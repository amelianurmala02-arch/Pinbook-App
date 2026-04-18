package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyOtpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        val inputOtp = findViewById<EditText>(R.id.inputOtp)
        val btnVerify = findViewById<Button>(R.id.btnVerify)

        btnVerify.setOnClickListener {

            val otp = inputOtp.text.toString()

            val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
            val savedOtp = prefs.getString("reset_otp", "")

            if (otp == savedOtp) {
                startActivity(Intent(this, ResetPasswordActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Kode OTP salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
