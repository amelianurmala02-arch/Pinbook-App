package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val btnSend = findViewById<Button>(R.id.btnSendCode)

        btnSend.setOnClickListener {
            val email = inputEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ⚠️ SIMULASI API — OTP selalu 123456
            val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
            prefs.edit()
                .putString("reset_email", email)
                .putString("reset_otp", "123456")
                .apply()

            startActivity(Intent(this, VerifyOtpActivity::class.java))
        }
    }
}
