package com.amelia.pinbook

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class SuccessActivity : AppCompatActivity() {

    private lateinit var qrImage: ImageView
    private lateinit var btnDownload: Button
    private lateinit var btnBack: ImageView
    private var qrBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // ambil id dari XML
        qrImage = findViewById(R.id.imgQRCode)
        btnDownload = findViewById(R.id.btnDownloadQR)
        btnBack = findViewById(R.id.btnBack)

        // buat QR code
        generateQR()

        // tombol download QR
        btnDownload.setOnClickListener {
            qrBitmap?.let { saveQR(it) }
        }

        // tombol panah kembali
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun generateQR() {

        val bookId = intent.getStringExtra("BOOK_ID") ?: "0"
        val title = intent.getStringExtra("JUDUL_BUKU") ?: "Unknown"
        val userId = intent.getStringExtra("USER_ID") ?: "0"

        val dataQR = "BOOK_ID:$bookId|TITLE:$title|USER_ID:$userId"

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(dataQR, BarcodeFormat.QR_CODE, 500, 500)

        val width = bitMatrix.width
        val height = bitMatrix.height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                )
            }
        }

        qrBitmap = bitmap
        qrImage.setImageBitmap(bitmap)
    }

    private fun saveQR(bitmap: Bitmap) {

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "pinbook_qr.png")
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        }

        val uri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let {

            val stream = contentResolver.openOutputStream(it)

            stream?.let {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                it.close()
            }

            Toast.makeText(this, "QR berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}