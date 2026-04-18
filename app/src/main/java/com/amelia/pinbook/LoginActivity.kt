package com.amelia.pinbook

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi Loading Dialog
        loadingDialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null)
        loadingDialog.setContentView(view)
        loadingDialog.setCancelable(false)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        val txtForgotPassword = findViewById<TextView>(R.id.txtForgotPassword)

        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tampilkan loading
            showLoading(true)
            btnLogin.isEnabled = false

            // 🔹 SEMENTARA LANGSUNG KE HOME TANPA CEK FIREBASE
            inputEmail.postDelayed({
                showLoading(false)
                btnLogin.isEnabled = true

                // Tandai sudah login
                val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
                prefs.edit().putBoolean("is_logged_in", true).apply()

                // Masuk ke HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

            }, 1500) // simulasi loading 1,5 detik
        }

        // Pindah ke RegisterActivity
        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Pindah ke ForgotPasswordActivity
        txtForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            if (!loadingDialog.isShowing) loadingDialog.show()
        } else {
            if (loadingDialog.isShowing) loadingDialog.dismiss()
        }
    }
}
