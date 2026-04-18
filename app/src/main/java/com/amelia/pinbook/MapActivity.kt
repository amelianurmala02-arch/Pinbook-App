package com.amelia.pinbook

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import android.webkit.JavascriptInterface
import android.content.Intent
import android.widget.Toast
import java.util.Locale
import android.content.res.Configuration
import com.amelia.pinbook.utils.StringHelper


class MapActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val lat = intent.getDoubleExtra("LAT", -6.200000)
        val lon = intent.getDoubleExtra("LON", 106.816666)

        webView = findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true

        // ✅ Tambahkan JavaScript Interface
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        webView.webViewClient = WebViewClient()

        webView.loadUrl("file:///android_asset/map.html?lat=$lat&lon=$lon")
    }

    // ✅ JavaScript Interface untuk komunikasi dengan WebView
    inner class WebAppInterface {

        @JavascriptInterface
        fun goToWelcome() {
            runOnUiThread {
                val intent = Intent(this@MapActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        @JavascriptInterface
        fun changeLanguage(countryCode: String) {
            runOnUiThread {
                val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)

                val countryName = when (countryCode.uppercase()) {
                    "ID" -> "Indonesia"
                    "MY" -> "Malaysia"
                    "SG" -> "Singapore"
                    "GB", "UK" -> "United Kingdom"
                    "US" -> "United States"
                    "JP" -> "Japan"
                    "FR" -> "France"
                    "DE" -> "Germany"
                    "KR" -> "Korea"
                    "CN" -> "China"
                    else -> "Unknown"
                }

                // Simpan country ke pinbook_pref
                prefs.edit()
                    .putString("user_country", countryName)
                    .putString("user_country_code", countryCode)
                    .apply()

                // ← TAMBAH INI: Convert country code ke language code & simpan
                val languageCode = when (countryCode.uppercase()) {
                    "ID" -> "in"
                    "MY" -> "ms"
                    "JP" -> "ja"
                    "FR" -> "fr"
                    "DE" -> "de"
                    "KR" -> "ko"
                    "CN" -> "zh"
                    else -> "en"
                }

                // ← TAMBAH INI: Panggil setLocale
                setLocale(languageCode)
            }

        }
    }

    // ✅ Fungsi untuk set locale
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        // Save preference
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        prefs.edit().putString("language", languageCode).apply()

        resources.updateConfiguration(config, resources.displayMetrics)

        Toast.makeText(
            this,
            StringHelper.getString(this, "change_language"),
            Toast.LENGTH_SHORT
        ).show()

    }

    // ✅ Load saved language saat activity dibuka
    private fun loadSavedLanguage() {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val languageCode = prefs.getString("language", "in") ?: "in"
        setLocaleWithoutRecreate(languageCode)
    }

    private fun setLocaleWithoutRecreate(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
