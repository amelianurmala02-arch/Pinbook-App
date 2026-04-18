package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amelia.pinbook.adapter.BukuAdapter
import com.amelia.pinbook.manager.FavoriteManager
import com.amelia.pinbook.model.Buku

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: BukuAdapter
    private val favoriteList = ArrayList<Buku>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        FavoriteManager.init(this)

        val recycler = findViewById<RecyclerView>(R.id.recyclerFavorite)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        adapter = BukuAdapter(
            favoriteList,
            onFavoriteClick = { buku ->
                FavoriteManager.removeFavorite(buku)
                loadFavorites()
            },
            onItemClick = { buku ->
                val i = Intent(this, BookDetailActivity::class.java)
                i.putExtra("buku", buku)
                startActivity(i)
            }
        )

        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        // 🔴 LOAD DATA PERTAMA KALI
        loadFavorites()
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun loadFavorites() {
        favoriteList.clear()
        favoriteList.addAll(FavoriteManager.getFavorites())

        // 🔴 PANGGIL refreshData() BUKAN notifyDataSetChanged()
        adapter.refreshData()
    }
}