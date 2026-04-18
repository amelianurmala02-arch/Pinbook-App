package com.amelia.pinbook

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.amelia.pinbook.data.database.AppDatabase
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScanQrActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var btnBack: ImageView
    private lateinit var loading: ProgressDialog
    private var bookId: String = ""

    companion object {
        private const val CAMERA_PERMISSION_REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

        barcodeView = findViewById(R.id.barcodeScanner)
        btnBack = findViewById(R.id.btnBack)

        // Ambil ID buku dari intent
        bookId = intent.getStringExtra("BOOK_ID") ?: ""

        loading = ProgressDialog(this)
        loading.setMessage("Memproses pengembalian buku...")
        loading.setCancelable(false)

        btnBack.setOnClickListener { finish() }

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            barcodeView.decodeContinuous(callback)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                barcodeView.decodeContinuous(callback)
            } else {
                Toast.makeText(this, "Izin kamera diperlukan", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private val callback = BarcodeCallback { result: BarcodeResult? ->
        if (result?.text != null) {
            runOnUiThread {
                loading.show()
                barcodeView.pause()

                // Update status di Room database
                val today = SimpleDateFormat("M/d/yyyy", Locale.getDefault()).format(Date())
                val db = AppDatabase.getInstance(this)

                lifecycleScope.launch {
                    if (bookId.isNotEmpty()) {
                        db.borrowingDao().returnBook(
                            id = bookId,
                            returnDate = today,
                            fine = "0"
                        )
                    }

                    loading.dismiss()

                    val intent = Intent(this@ScanQrActivity, SuccessActivity::class.java)
                    intent.putExtra("MESSAGE", "Pengembalian buku berhasil")
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}