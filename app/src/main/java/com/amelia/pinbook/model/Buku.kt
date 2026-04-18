package com.amelia.pinbook.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Buku(
    val id: String,
    val judul: String,
    val deskripsi: String,
    val gambar: Int,
    val stok: Int,
    var isFavorite: Boolean = false,
    val kategori: String = "Pendidikan"
) : Parcelable
