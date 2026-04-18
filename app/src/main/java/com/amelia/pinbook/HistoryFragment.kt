package com.amelia.pinbook

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amelia.pinbook.adapter.BorrowingAdapter
import com.amelia.pinbook.data.database.AppDatabase
import com.amelia.pinbook.data.entity.BorrowingEntity
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BorrowingAdapter
    private lateinit var emptyText: TextView

    private val historyList = mutableListOf<BorrowingEntity>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvBorrowing)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = BorrowingAdapter(
            requireContext(),
            historyList
        ) { item ->

            lifecycleScope.launch {
                val db = AppDatabase.getInstance(requireContext())
                db.borrowingDao().deleteById(item.id)

                val position = historyList.indexOf(item)
                if (position != -1) {
                    historyList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }

                emptyText.visibility =
                    if (historyList.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        recyclerView.adapter = adapter

        emptyText = view.findViewById(R.id.tvEmpty)

        loadHistoryData()
    }

    private fun loadHistoryData() {
        val db = AppDatabase.getInstance(requireContext())

        lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val data = db.borrowingDao().getHistory()

            historyList.clear()
            historyList.addAll(data)
            adapter.notifyDataSetChanged()

            emptyText.visibility =
                if (historyList.isEmpty()) View.VISIBLE else View.GONE
        }

    }
}
