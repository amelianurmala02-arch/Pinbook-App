package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Splash2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        // Animasi logo dan nama aplikasi
        val logo = findViewById<ImageView>(R.id.logoImage)
        val appName = findViewById<ImageView>(R.id.appName)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(fadeIn)
        appName.startAnimation(fadeIn)

        // Delay sebentar sebelum pindah ke halaman permission
        Handler(Looper.getMainLooper()).postDelayed({
            moveNext()
        }, 1200)
    }

    private fun moveNext() {
        // Selalu menuju LocationPermissionActivity dulu
        val intent = Intent(this, LocationPermissionActivity::class.java)
        startActivity(intent)
        finish()
    }
}
