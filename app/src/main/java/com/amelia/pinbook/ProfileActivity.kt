package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = java.util.Locale(lang)
        java.util.Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // ===== LOGOUT =====
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {

            val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
            prefs.edit().putBoolean("is_logged_in", false).apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // ===== BOTTOM NAVIGATION =====
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Tandai menu Profil sebagai aktif
        bottomNav.selectedItemId = R.id.nav_profile

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }

                R.id.nav_shelf -> {
                    startActivity(Intent(this, BookRackActivity::class.java))
                    true
                }

                R.id.nav_ai -> {
                    startActivity(Intent(this, AiChatActivity::class.java))
                    true
                }

                R.id.nav_profile -> {
                    // Sudah di halaman ini
                    true
                }

                else -> false
            }
        }
    }
}

