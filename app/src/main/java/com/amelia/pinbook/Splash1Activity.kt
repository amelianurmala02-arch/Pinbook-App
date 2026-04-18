package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash1Activity : AppCompatActivity() {

    private val DURATION = 800L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Splash2Activity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, DURATION)
    }
}
