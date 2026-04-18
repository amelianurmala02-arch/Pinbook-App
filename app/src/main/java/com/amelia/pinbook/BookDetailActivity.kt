package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amelia.pinbook.manager.FavoriteManager
import com.amelia.pinbook.model.Buku
import java.util.Locale

class BookDetailActivity : AppCompatActivity() {

    private lateinit var imgCover: ImageView
    private lateinit var ivFavorite: ImageView
    private lateinit var txtStock: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnPinjam: Button

    private lateinit var buku: Buku
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        imgCover = findViewById(R.id.imgCover)
        ivFavorite = findViewById(R.id.ivFavorite)
        txtStock = findViewById(R.id.txtStock)
        tvTitle = findViewById(R.id.tvBookTitle)
        tvDescription = findViewById(R.id.tvDescription)
        btnPinjam = findViewById(R.id.btnPinjam)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        buku = intent.getParcelableExtra("buku")
            ?: run {
                Toast.makeText(this, "Data buku tidak ditemukan", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

        tvTitle.text = buku.judul
        tvDescription.text = buku.deskripsi
        txtStock.text = "Stok ${buku.stok}"
        imgCover.setImageResource(buku.gambar)

        // 🔴 CEK STATUS FAVORITE DARI MANAGER (BUKAN DARI OBJECT BUKU)
        isFavorite = FavoriteManager.isFavorite(buku)
        updateFavoriteUI()

        ivFavorite.setOnClickListener {
            isFavorite = !isFavorite

            if (isFavorite) {
                FavoriteManager.addFavorite(buku)
            } else {
                FavoriteManager.removeFavorite(buku)
            }

            updateFavoriteUI()

            Toast.makeText(
                this,
                if (isFavorite) "Ditambahkan ke Favorit"
                else "Dihapus dari Favorit",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnPinjam.setOnClickListener {
            val intent = Intent(this, BorrowBookActivity::class.java)
            intent.putExtra("buku", buku)
            startActivity(intent)
        }

    }

    private fun updateFavoriteUI() {
        ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (isFavorite)
                    R.drawable.ic_love_filled
                else
                    R.drawable.ic_love_outline
            )
        )
    }
}
