package com.amelia.pinbook.manager

import android.content.Context
import android.content.SharedPreferences
import com.amelia.pinbook.R
import com.amelia.pinbook.model.Buku
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavoriteManager {

    private const val PREF_NAME = "FavoritePrefs"
    private const val KEY_FAVORITES = "favorites"
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    // 🔴 MAPPING JUDUL KE GAMBAR
    private val bookImageMap = mapOf(
        "Pemrograman Berbasis Mobile" to R.drawable.book1,
        "Pemrograman Web" to R.drawable.book2,
        "Rekayasa Perangkat Lunak" to R.drawable.book3,
        "Jaringan Komputer" to R.drawable.book4,
        "Probabilitas dan Statiska" to R.drawable.book5,
        "Kalkulus" to R.drawable.book6,
        "Mariposa" to R.drawable.book_mariposa,
        "Kisah Untuk Geri" to R.drawable.book_kisah_untuk_geri,
        "Teluk Alaska" to R.drawable.book_teluk_alaska,
        "Samuel" to R.drawable.book_samuel,
        "Dignitate" to R.drawable.book_dignitate,
        "Dilan" to R.drawable.book_dilan
    )

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun addFavorite(buku: Buku) {
        val favoriteList = getFavorites().toMutableList()
        if (favoriteList.none { it.judul == buku.judul }) {
            favoriteList.add(buku)
            saveFavorites(favoriteList)
        }
    }

    fun removeFavorite(buku: Buku) {
        val favoriteList = getFavorites().toMutableList()
        favoriteList.removeAll { it.judul == buku.judul }
        saveFavorites(favoriteList)
    }

    fun isFavorite(buku: Buku): Boolean {
        return getFavorites().any { it.judul == buku.judul }
    }

    fun getFavorites(): List<Buku> {
        val json = sharedPreferences.getString(KEY_FAVORITES, null)
        return if (json != null) {
            try {
                val type = object : TypeToken<List<Buku>>() {}.type
                val list: List<Buku> = gson.fromJson(json, type)

                // 🔴 FIX GAMBAR DARI MAP
                list.map { buku ->
                    val correctImage = bookImageMap[buku.judul] ?: R.drawable.book1
                    buku.copy(gambar = correctImage)
                }
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    private fun saveFavorites(favoriteList: List<Buku>) {
        val json = gson.toJson(favoriteList)
        sharedPreferences.edit().putString(KEY_FAVORITES, json).apply()
    }
}
