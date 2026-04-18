package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)

        // Ambil negara dari DetectingLocationActivity jika ada
        val country = prefs.getString("user_country", "Unknown") ?: "Unknown"

        val flag = findViewById<ImageView>(R.id.flagImage)
        val welcomeText = findViewById<TextView>(R.id.welcomeText)

        // ===========================
        // 🎌 LOGIC GANTI BAHASA + BENDERA
        // ===========================
        when (country) {
            "Indonesia" -> {
                flag.setImageResource(R.drawable.flag_indonesia)
                welcomeText.text = "Selamat datang di layanan pinjaman buku online"
            }
            "Malaysia" -> {
                flag.setImageResource(R.drawable.flag_malaysia)
                welcomeText.text = "Selamat datang ke perkhidmatan pinjaman buku dalam talian"
            }
            "Singapore" -> {
                flag.setImageResource(R.drawable.flag_singapore)
                welcomeText.text = "Welcome to the online book loan service"
            }
            "United Kingdom" -> {
                flag.setImageResource(R.drawable.flag_uk)
                welcomeText.text = "Welcome to the online book loan service"
            }
            "United States" -> {
                flag.setImageResource(R.drawable.flag_us)
                welcomeText.text = "Welcome to the online book loan service"
            }
            "Japan" -> {
                flag.setImageResource(R.drawable.flag_japan)
                welcomeText.text = "オンライン図書館サービスへようこそ"
            }
            "France" -> {
                flag.setImageResource(R.drawable.flag_france)
                welcomeText.text = "Bienvenue dans le service de prêt de livres en ligne"
            }
            "Germany" -> {
                flag.setImageResource(R.drawable.flag_germany)
                welcomeText.text = "Willkommen beim Online-Buchleihservice"
            }
            "Korea" -> {
                flag.setImageResource(R.drawable.flag_korea)
                welcomeText.text = "온라인 도서 대출 서비스에 오신 것을 환영합니다"
            }
            "China" -> {
                flag.setImageResource(R.drawable.flag_china)
                welcomeText.text = "欢迎使用在线图书借阅服务"
            }
            else -> {
                flag.setImageResource(R.drawable.flag_world)
                welcomeText.text = "Welcome to the online book loan service"
            }
        }

        // Simpan bahwa welcome sudah tampil agar tidak muncul lagi pada kunjungan normal
        prefs.edit().putBoolean("welcome_shown", true).apply()

        // ===========================
        // DELAY 3 DETIK SEBELUM KE LOGIN
        // ===========================
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000) // 3 detik delay
    }
}
