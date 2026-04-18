package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookRackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = java.util.Locale(lang)
        java.util.Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_rack)

        // ===== BOTTOM NAVIGATION =====
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Tandai Rak Buku sebagai aktif
        bottomNav.selectedItemId = R.id.nav_shelf

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }

                R.id.nav_shelf -> {
                    // Sudah di Rak Buku
                    true
                }

                R.id.nav_ai -> {
                    startActivity(Intent(this, AiChatActivity::class.java))
                    true
                }

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }

        // ===== MENU RAK BUKU =====
        findViewById<android.widget.LinearLayout>(R.id.menuPengembalian).setOnClickListener {
            startActivity(Intent(this, ReturnBookActivity::class.java))
        }

        findViewById<android.widget.LinearLayout>(R.id.menuRiwayat).setOnClickListener {
            startActivity(Intent(this, BorrowingHistoryActivity::class.java))
        }

        findViewById<android.widget.LinearLayout>(R.id.menuFavorite).setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }
}
