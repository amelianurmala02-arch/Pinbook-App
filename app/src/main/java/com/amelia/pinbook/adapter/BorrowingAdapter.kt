package com.amelia.pinbook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amelia.pinbook.R
import com.amelia.pinbook.ReturnBookActivity
import com.amelia.pinbook.data.entity.BorrowingEntity

class BorrowingAdapter(
    private val context: Context,
    private val borrowingList: MutableList<BorrowingEntity>,
    private val onDeleteClick: (BorrowingEntity) -> Unit
) : RecyclerView.Adapter<BorrowingAdapter.BorrowingViewHolder>() {

    inner class BorrowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBook: ImageView = itemView.findViewById(R.id.imgBook)
        val txtBookTitle: TextView = itemView.findViewById(R.id.txtBookTitle)
        val txtBorrowDate: TextView = itemView.findViewById(R.id.txtBorrowDate)
        val txtReturnDate: TextView = itemView.findViewById(R.id.txtReturnDate)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_borrow_card, parent, false)
        return BorrowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BorrowingViewHolder, position: Int) {
        val item = borrowingList[position]

        // Set text
        holder.txtBookTitle.text = item.title
        holder.txtBorrowDate.text = "Pinjam: ${item.borrowDate}"
        holder.txtReturnDate.text = "Kembali: ${item.returnDate}"
        holder.txtStatus.text = item.status

        // 🔥 LOAD GAMBAR - INI YANG PENTING!
        if (item.coverImage.isNotEmpty()) {
            try {
                // Convert string ke int (karena gambar dari drawable)
                // reset dulu
                holder.imgBook.setImageResource(R.drawable.ic_logo)

                val resId = context.resources.getIdentifier(
                    item.coverImage,
                    "drawable",
                    context.packageName
                )

                if (resId != 0) {
                    holder.imgBook.setImageResource(resId)
                }

            } catch (e: Exception) {
                // Kalau error, pakai gambar default
                holder.imgBook.setImageResource(R.drawable.ic_logo)
            }
        } else {
            // Kalau kosong, pakai gambar default
            holder.imgBook.setImageResource(R.drawable.ic_logo)
        }

        // Click listener
        holder.itemView.setOnClickListener {
            if (item.status.lowercase() == "dipinjam") {
                val intent = Intent(context, ReturnBookActivity::class.java)
                intent.putExtra("BORROW_ID", item.id)
                context.startActivity(intent)
            }
        }
        // tombol hapus cuma buat riwayat
        holder.btnDelete.visibility =
            if (item.status == "dikembalikan") View.VISIBLE else View.GONE

        holder.btnDelete.setOnClickListener {
            onDeleteClick(item)
        }

    }

    override fun getItemCount(): Int = borrowingList.size
}
