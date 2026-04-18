package com.amelia.pinbook

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.findViewById(R.id.tvChatMessage)
        val container: LinearLayout = itemView.findViewById(R.id.chatContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = messages[position]
        holder.tvMessage.text = msg.text

        if (msg.isUser) {
            holder.container.gravity = Gravity.END
            holder.tvMessage.setBackgroundResource(R.drawable.bg_chat_user)
            holder.tvMessage.setTextColor(android.graphics.Color.BLACK)
        } else {
            holder.container.gravity = Gravity.START
            holder.tvMessage.setBackgroundResource(R.drawable.bg_chat_ai)
            holder.tvMessage.setTextColor(android.graphics.Color.WHITE)
        }
    }

    override fun getItemCount() = messages.size
}