package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.app.Dialog
import android.view.LayoutInflater

class RegisterActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = java.util.Locale(lang)
        java.util.Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi loading dialog
        loadingDialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null)
        loadingDialog.setContentView(view)
        loadingDialog.setCancelable(false)

        auth = FirebaseAuth.getInstance()

        val inputNama = findViewById<EditText>(R.id.inputNama)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val inputKonfirmasi = findViewById<EditText>(R.id.inputKonfirmasiPassword)
        val btnDaftar = findViewById<Button>(R.id.btnDaftar)
        val txtMasuk = findViewById<TextView>(R.id.txtMasuk)

        btnDaftar.setOnClickListener {
            val nama = inputNama.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val pass = inputPassword.text.toString()
            val konfirmasi = inputKonfirmasi.text.toString()

            if (nama.isEmpty() || email.isEmpty() || pass.isEmpty() || konfirmasi.isEmpty()) {
                toast("Semua kolom wajib diisi")
                return@setOnClickListener
            }

            if (pass != konfirmasi) {
                toast("Password tidak cocok")
                return@setOnClickListener
            }

            showLoading(true)

            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    showLoading(false)
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.updateProfile(
                            com.google.firebase.auth.UserProfileChangeRequest.Builder()
                                .setDisplayName(nama)
                                .build()
                        )
                        // 🔹 Kirim email verifikasi asli
                        user?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                toast("Email verifikasi dikirim ke $email")
                                startActivity(Intent(this, EmailVerificationActivity::class.java))
                                finish()
                            }
                            ?.addOnFailureListener {
                                toast("Gagal kirim email verifikasi: ${it.message}")
                            }

                    } else {
                        toast("Gagal daftar: ${task.exception?.message}")
                    }
                }
        }

        txtMasuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    // Fungsi generate kode 6-digit
    private fun generateVerificationCode(): String {
        return (100000..999999).random().toString()
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            if (!loadingDialog.isShowing) loadingDialog.show()
        } else {
            if (loadingDialog.isShowing) loadingDialog.dismiss()
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
