package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EmailVerificationActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private val resendInterval = 60_000L // 1 menit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)

        val inputKode = findViewById<EditText>(R.id.inputKode)
        val btnVerifikasi = findViewById<Button>(R.id.btnVerifikasi)
        val btnKirimUlang = findViewById<Button>(R.id.btnKirimUlang)
        val txtInfo = findViewById<TextView>(R.id.txtInfo)

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        val userId = user.uid

        txtInfo.text = "Kode verifikasi telah dikirim ke email ${user.email}"

        // Tombol verifikasi kode
        btnVerifikasi.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            user?.reload()?.addOnCompleteListener {
                if (user.isEmailVerified) {
                    // Tandai user sudah login
                    val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
                    prefs.edit().putBoolean("is_logged_in", true).apply()

                    startActivity(Intent(this, HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                } else {
                    Toast.makeText(this, "Email belum diverifikasi, cek inbox kamu", Toast.LENGTH_SHORT).show()
                }
            }
        }

// Tombol kirim ulang
        btnKirimUlang.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            btnKirimUlang.isEnabled = false
            user?.sendEmailVerification()?.addOnSuccessListener {
                Toast.makeText(this, "Email verifikasi dikirim ulang", Toast.LENGTH_SHORT).show()
                startResendTimer(btnKirimUlang)
            }?.addOnFailureListener {
                Toast.makeText(this, "Gagal kirim ulang: ${it.message}", Toast.LENGTH_SHORT).show()
                btnKirimUlang.isEnabled = true
            }


        // Generate kode baru
            val code = (100000..999999).random().toString()
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(userId)
                .update("verificationCode", code)
                .addOnSuccessListener {
                    Toast.makeText(this, "Kode baru dikirim ke email (simulasi)", Toast.LENGTH_SHORT).show()
                    startResendTimer(btnKirimUlang)
                    // 🔹 TODO: hubungkan dengan backend atau service email nyata untuk kirim kode
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal update kode: ${it.message}", Toast.LENGTH_SHORT).show()
                    btnKirimUlang.isEnabled = true
                }
        }

        // Mulai timer pertama kali
        startResendTimer(btnKirimUlang)
    }

    private fun startResendTimer(btnKirimUlang: Button) {
        btnKirimUlang.isEnabled = false
        timer?.cancel()
        timer = object : CountDownTimer(resendInterval, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                btnKirimUlang.text = "Kirim Ulang (${millisUntilFinished / 1000}s)"
            }

            override fun onFinish() {
                btnKirimUlang.text = "Kirim Ulang Email"
                btnKirimUlang.isEnabled = true
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
