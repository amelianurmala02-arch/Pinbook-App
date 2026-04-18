package com.amelia.pinbook

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationPermissionActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Jika izin sudah diberikan, langsung lanjut detecting
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            goToDetecting()
            return
        }

        setContentView(R.layout.activity_location_permission)

        val btnAllow = findViewById<Button>(R.id.btnAllowLocation)
        val btnDeny = findViewById<Button>(R.id.btnDenyLocation)

        // Tombol Izinkan → tampilkan dialog resmi lokasi Android
        btnAllow.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_CODE
            )
        }

        // Tombol Tolak → langsung ke Welcome (Bahasa Inggris)
        btnDeny.setOnClickListener {
            goToWelcomeEnglish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Izin diberikan → lanjut ke halaman mencari lokasi
                goToDetecting()

            } else {
                // Pengguna menolak dari dialog Android → Welcome Inggris
                goToWelcomeEnglish()
            }
        }
    }

    private fun goToDetecting() {
        startActivity(Intent(this, DetectingLocationActivity::class.java))
        finish()
    }

    private fun goToWelcomeEnglish() {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra("LANG", "EN") // bahasa internasional
        startActivity(intent)
        finish()
    }
}
