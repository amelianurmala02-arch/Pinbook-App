package com.amelia.pinbook

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class AiChatActivity : AppCompatActivity() {

    private lateinit var rvChat: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageView
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    private val koleksiBuku = """
        Koleksi buku di Pinbook:
        KATEGORI PENDIDIKAN: Pemrograman Berbasis Mobile, Pemrograman Web, Rekayasa Perangkat Lunak, Jaringan Komputer, Probabilitas dan Statistika, Kalkulus.
        KATEGORI NOVEL: Mariposa, Kisah Untuk Geri, Teluk Alaska, Samuel, Dignitate, Dilan.
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)

        rvChat = findViewById(R.id.rvChat)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        rvChat.layoutManager = layoutManager
        chatAdapter = ChatAdapter(messages)
        rvChat.adapter = chatAdapter

        // Pesan pembuka Bookie
        addMessage("Halo! Aku Bookie, asisten buku Pinbook 📚 Tanya aku untuk rekomendasi buku, atau ceritain genre yang kamu suka!", false)

        btnSend.setOnClickListener {
            val userMsg = etMessage.text.toString().trim()
            if (userMsg.isEmpty()) return@setOnClickListener
            etMessage.setText("")
            addMessage(userMsg, true)
            sendToAI(userMsg)
        }
    }

    private fun addMessage(text: String, isUser: Boolean) {
        messages.add(ChatMessage(text, isUser))
        chatAdapter.notifyItemInserted(messages.size - 1)
        rvChat.scrollToPosition(messages.size - 1)
    }

    private fun sendToAI(userMessage: String) {
        addMessage("Bookie sedang mengetik...", false)
        val loadingIndex = messages.size - 1

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiKey = "AIzaSyDjGMay4eXMt_x2IT1BhbBnLPgldW-vNJI" // ← API key kamu
                val url = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$apiKey")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "application/json")
                conn.doOutput = true
                conn.connectTimeout = 15000
                conn.readTimeout = 15000

                val systemPrompt = """
                Kamu adalah Bookie, asisten rekomendasi buku untuk aplikasi perpustakaan Pinbook.
                $koleksiBuku
                Tugasmu: rekomendasikan buku dari koleksi di atas sesuai minat user.
                Jawab singkat, ramah, dalam Bahasa Indonesia.
            """.trimIndent()

                val body = JSONObject().apply {
                    put("contents", JSONArray().put(JSONObject().apply {
                        put("parts", JSONArray()
                            .put(JSONObject().put("text", "$systemPrompt\n\nUser: $userMessage"))
                        )
                    }))
                }

                OutputStreamWriter(conn.outputStream).use { it.write(body.toString()) }

                val responseCode = conn.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = conn.inputStream.bufferedReader().readText()
                    val aiReply = JSONObject(response)
                        .getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")

                    withContext(Dispatchers.Main) {
                        messages[loadingIndex] = ChatMessage(aiReply, false)
                        chatAdapter.notifyItemChanged(loadingIndex)
                        rvChat.scrollToPosition(messages.size - 1)
                    }
                } else {
                    val errorResponse = conn.errorStream?.bufferedReader()?.readText() ?: "Unknown error"
                    withContext(Dispatchers.Main) {
                        messages[loadingIndex] = ChatMessage("Error $responseCode: $errorResponse", false)
                        chatAdapter.notifyItemChanged(loadingIndex)
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    messages[loadingIndex] = ChatMessage("Error: ${e.message}", false)
                    chatAdapter.notifyItemChanged(loadingIndex)
                }
            }
        }
    }
}