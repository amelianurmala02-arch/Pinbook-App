package com.amelia.pinbook

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.amelia.pinbook.model.Buku
import java.util.Calendar
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.amelia.pinbook.data.database.AppDatabase
import com.amelia.pinbook.data.entity.BorrowingEntity
import com.amelia.pinbook.NotificationHelper
import java.util.Locale


class BorrowBookActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var txtDatePinjam: TextView
    private lateinit var txtDateKembali: TextView
    private lateinit var datePinjam: LinearLayout
    private lateinit var dateKembali: LinearLayout
    private lateinit var btnKonfirmasi: Button
    private lateinit var tvJudulBuku: TextView
    private lateinit var imgCover: ImageView
    private lateinit var loading: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow_book)

        // ===== BIND VIEW =====
        btnBack = findViewById(R.id.btnBack)
        txtDatePinjam = findViewById(R.id.txtDatePinjam)
        txtDateKembali = findViewById(R.id.txtDateKembali)
        datePinjam = findViewById(R.id.datePinjam)
        dateKembali = findViewById(R.id.dateKembali)
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi)
        tvJudulBuku = findViewById(R.id.tvJudulBuku)
        imgCover = findViewById(R.id.imgCover)

        // ===== AMBIL DATA BUKU =====
        val buku = intent.getParcelableExtra<Buku>("buku")
        if (buku == null) {
            Toast.makeText(this, "Data buku tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // ===== SET DATA KE UI =====
        tvJudulBuku.text = buku.judul
        imgCover.setImageResource(buku.gambar)

        // ===== LOADING =====
        loading = ProgressDialog(this)
        loading.setMessage("Menyimpan data...")
        loading.setCancelable(false)

        btnBack.setOnClickListener { finish() }

        datePinjam.setOnClickListener {
            showDatePicker { txtDatePinjam.text = it }
        }

        dateKembali.setOnClickListener {
            showDatePicker { txtDateKembali.text = it }
        }

        btnKonfirmasi.setOnClickListener {
            // Validasi tanggal
            val tanggalPinjam = txtDatePinjam.text.toString()
            val tanggalKembali = txtDateKembali.text.toString()

            if (tanggalPinjam == "mm/dd/yyyy" || tanggalKembali == "mm/dd/yyyy") {
                Toast.makeText(this, "Tanggal belum dipilih!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loading.show()

            // 🔥 SIMPAN KE DATABASE
            val db = AppDatabase.getInstance(this)
            val dao = db.borrowingDao()

            val borrowing = BorrowingEntity(
                id = System.currentTimeMillis().toString(),
                title = buku.judul,
                borrower = "User",
                borrowDate = tanggalPinjam,
                returnDate = tanggalKembali,  // ✅ FIX: pakai tanggal kembali
                status = "dipinjam",
                fine = "0",
                coverImage = buku.gambar.toString()  // 🆕 SIMPAN GAMBAR!
            )

            lifecycleScope.launch {
                try {
                    dao.insertBorrowing(borrowing)
                    loading.dismiss()

                    // ↓ TAMBAH DI SINI
                    NotificationHelper.send(
                        context = this@BorrowBookActivity,
                        notifId = borrowing.id.hashCode(),
                        title = "📚 Peminjaman Berhasil!",
                        message = "Kamu meminjam \"${buku.judul}\". Jangan lupa kembalikan sebelum $tanggalKembali!"
                    )

                    Toast.makeText(
                        this@BorrowBookActivity,
                        "Peminjaman berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()

                    Toast.makeText(
                        this@BorrowBookActivity,
                        "Peminjaman berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@BorrowBookActivity, SuccessActivity::class.java)

                    intent.putExtra("BOOK_ID", buku.id)
                    intent.putExtra("JUDUL_BUKU", buku.judul)
                    intent.putExtra("USER_ID", "1")

                    startActivity(intent)

                } catch (e: Exception) {
                    loading.dismiss()
                    Toast.makeText(
                        this@BorrowBookActivity,
                        "Gagal menyimpan: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showDatePicker(onSelect: (String) -> Unit) {
        val c = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                onSelect("${month + 1}/$day/$year")
            },
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}