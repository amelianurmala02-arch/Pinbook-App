package com.amelia.pinbook

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class DetectingLocationActivity : AppCompatActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var detectingText: TextView
    private lateinit var retryButton: Button
    private lateinit var googleMap: GoogleMap
    private val handler = Handler(Looper.getMainLooper())
    private var dotsRunnable: Runnable? = null
    private var locationDetected = false
    private var retryCount = 0
    private val maxRetry = 3
    private val GPS_REQUEST_CODE = 1001

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                checkGPSAndStartUpdates()
            } else {
                Toast.makeText(this, "Permission lokasi diperlukan.", Toast.LENGTH_LONG).show()
                goToWelcomeDefault()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detecting_location)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        detectingText = findViewById(R.id.detectingText)
        retryButton = findViewById(R.id.btnRetry)

        // Setup MapFragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            googleMap = map
        }

        animateIcon()
        animateTextDots()

        retryButton.setOnClickListener {
            retryButton.visibility = View.GONE
            retryCount = 0
            locationDetected = false
            checkPermissionAndStart()
        }

        checkPermissionAndStart()
    }

    private fun animateIcon() {
        val icon = findViewById<ImageView>(R.id.locationIcon)
        val fade = AlphaAnimation(0.3f, 1.0f).apply {
            duration = 700
            repeatMode = AlphaAnimation.REVERSE
            repeatCount = AlphaAnimation.INFINITE
        }
        icon.startAnimation(fade)
    }

    private fun animateTextDots() {
        var dotsCount = 0
        val maxDots = 10 // jumlah titik maksimum sebelum reset
        dotsRunnable = object : Runnable {
            override fun run() {
                dotsCount = (dotsCount + 1) % (maxDots + 1) // 0..10
                val dots = ".".repeat(dotsCount)
                detectingText.text = "Detecting your location$dots"
                handler.postDelayed(this, 300) // update tiap 300ms
            }
        }
        handler.post(dotsRunnable!!)
    }

    private fun checkPermissionAndStart() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                checkGPSAndStartUpdates()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun checkGPSAndStartUpdates() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)

        client.checkLocationSettings(builder.build())
            .addOnSuccessListener { startLocationUpdates() }
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    exception.startResolutionForResult(this, GPS_REQUEST_CODE)
                } else {
                    Toast.makeText(this, "GPS tidak dapat diaktifkan.", Toast.LENGTH_LONG).show()
                    goToWelcomeDefault()
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.lastLocation
                if (location != null && !locationDetected) {
                    locationDetected = true
                    saveLocation(location)
                    stopLocationUpdates()
                    showLocationOnMap(location)
                    // ➤ MODIFIKASI: Kirim ke MapActivity
                    goToMapActivity(location)
                }
            }
        }

        fusedLocation.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        handler.postDelayed({
            if (!locationDetected) {
                stopLocationUpdates()
                if (retryCount < maxRetry) {
                    retryCount++
                    startLocationUpdates()
                } else {
                    retryButton.visibility = View.VISIBLE
                }
            }
        }, 10000)
    }

    private fun showLocationOnMap(location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)
        googleMap.clear()
        googleMap.addMarker(MarkerOptions().position(latLng).title("Your Location"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

    @SuppressLint("MissingPermission")
    private fun stopLocationUpdates() {
        fusedLocation.removeLocationUpdates(locationCallback)
    }

    private fun saveLocation(location: Location) {
        val country = getCountryName(location)
        val pref = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
        pref.edit()
            .putString("user_country", country)
            .putFloat("user_lat", location.latitude.toFloat())
            .putFloat("user_lng", location.longitude.toFloat())
            .apply()
    }

    private fun getCountryName(location: Location): String {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            address?.get(0)?.countryName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun stopAnimation() {
        dotsRunnable?.let { handler.removeCallbacks(it) }
        val icon = findViewById<ImageView>(R.id.locationIcon)
        icon.clearAnimation()
    }

    private fun updateLanguage(country: String) {
        val locale = when (country) {
            "Indonesia" -> Locale("id")
            "United States" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    // ➤ MODIFIKASI: Kirim ke MapActivity
    private fun goToMapActivity(location: Location) {
        stopAnimation()
        handler.removeCallbacksAndMessages(null)

        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("LAT", location.latitude)
        intent.putExtra("LON", location.longitude)
        startActivity(intent)
        finish() // agar DetectingLocationActivity selesai
    }

    private fun goToWelcome() {
        stopAnimation()
        handler.removeCallbacksAndMessages(null)

        val pref = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
        val country = pref.getString("user_country", "Unknown") ?: "Unknown"
        updateLanguage(country)

        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    private fun goToWelcomeDefault() {
        stopAnimation()
        handler.removeCallbacksAndMessages(null)

        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GPS_REQUEST_CODE) {
            startLocationUpdates()
        }
    }
}
