package com.amelia.pinbook

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.content.Intent

class ReturnBookActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnScan: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "in") ?: "in"
        val locale = java.util.Locale(lang)
        java.util.Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_book)

        // hubungkan ke XML
        btnBack = findViewById(R.id.btnBack)
        btnScan = findViewById(R.id.btnScan)

        // tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // tombol scan
        btnScan.setOnClickListener {
            val bookId = intent.getStringExtra("BORROW_ID") ?: "" // ← ganti ke BORROW_ID
            val intent = Intent(this, ScanQrActivity::class.java)
            intent.putExtra("BOOK_ID", bookId)
            startActivity(intent)
        }
    }
}