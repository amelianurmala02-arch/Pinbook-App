package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val newPass = findViewById<EditText>(R.id.inputNewPassword)
        val confirm = findViewById<EditText>(R.id.inputConfirmPassword)
        val btnSave = findViewById<Button>(R.id.btnSavePassword)

        btnSave.setOnClickListener {

            val p1 = newPass.text.toString()
            val p2 = confirm.text.toString()

            if (p1.length < 5) {
                Toast.makeText(this, "Password minimal 5 karakter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (p1 != p2) {
                Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
            prefs.edit()
                .putString("user_password", p1)
                .apply()

            Toast.makeText(this, "Password berhasil diganti!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
