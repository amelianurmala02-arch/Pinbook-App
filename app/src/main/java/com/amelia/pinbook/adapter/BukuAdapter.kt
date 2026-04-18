package com.amelia.pinbook.adapter

import com.amelia.pinbook.model.Buku
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amelia.pinbook.R
import com.amelia.pinbook.manager.FavoriteManager

class BukuAdapter(
    private var originalList: ArrayList<Buku>, // Ganti nama untuk filter
    private val onFavoriteClick: (Buku) -> Unit,
    private val onItemClick: (Buku) -> Unit
) : RecyclerView.Adapter<BukuAdapter.ViewHolder>() {

    private var filteredList = ArrayList<Buku>() // List untuk hasil filter

    init {
        filteredList.addAll(originalList) // Awalnya tampilkan semua
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLove: ImageView = itemView.findViewById(R.id.btnLove)
        val imgCover: ImageView = itemView.findViewById(R.id.imgCover)
        val tvJudul: TextView = itemView.findViewById(R.id.txtJudul)
        val tvStok: TextView = itemView.findViewById(R.id.txtStok)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_buku, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buku = filteredList[position]

        holder.tvJudul.text = buku.judul
        holder.imgCover.setImageResource(buku.gambar)
        holder.tvStok.text = "Stok ${buku.stok}"

        // 🔴 BAGIAN INI YANG DIUBAH
        val isFavorite = FavoriteManager.isFavorite(buku)
        holder.imgLove.setImageResource(
            if (isFavorite)
                R.drawable.ic_love_filled
            else
                R.drawable.ic_love_outline
        )
        // 🔴 SAMPAI SINI

        // ✅ KLIK LOVE (TANPA TOGGLE DI SINI)
        holder.imgLove.setOnClickListener {
            onFavoriteClick(buku)
            notifyDataSetChanged() // refresh aman utk filteredList
        }

        // klik item → detail
        holder.itemView.setOnClickListener {
            onItemClick(buku)
        }
    }

    override fun getItemCount(): Int = filteredList.size // Gunakan filteredList

    // ===========================
    // FUNGSI FILTER UNTUK SEARCH
    // ===========================
    fun filter(query: String) {
        filteredList.clear()

        if (query.isEmpty()) {
            // Jika search kosong, tampilkan semua
            filteredList.addAll(originalList)
        } else {
            val lowerQuery = query.lowercase()
            for (buku in originalList) {
                // Cari berdasarkan judul (bisa diubah ke deskripsi juga)
                if (buku.judul.lowercase().contains(lowerQuery)) {
                    filteredList.add(buku)
                }
            }
        }

        notifyDataSetChanged() // Refresh RecyclerView
    }

    // OPTIONAL: Fungsi untuk update data dari luar
    fun updateList(newList: ArrayList<Buku>) {
        originalList.clear()
        originalList.addAll(newList)
        filter("") // Reset filter
    }

    // 🔴 TAMBAHKAN FUNGSI BARU INI
    fun refreshData() {
        filteredList.clear()
        filteredList.addAll(originalList)
        notifyDataSetChanged()
    }

    fun filterByKategori(kategori: String) {
        filteredList.clear()
        if (kategori == "All") {
            filteredList.addAll(originalList)
        } else {
            for (buku in originalList) {
                if (buku.kategori == kategori) {
                    filteredList.add(buku)
                }
            }
        }
        notifyDataSetChanged()
    }

}