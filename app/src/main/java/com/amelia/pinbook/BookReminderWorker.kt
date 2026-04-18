package com.amelia.pinbook

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.amelia.pinbook.data.database.AppDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BookReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val ongoingBooks = db.borrowingDao().getOngoing()

        if (ongoingBooks.isEmpty()) return Result.success()

        val sdf = SimpleDateFormat("M/d/yyyy", Locale.getDefault())
        val today = sdf.format(Date())
        val todayDate = sdf.parse(today) ?: return Result.success()

        ongoingBooks.forEach { book ->
            try {
                val returnDate = sdf.parse(book.returnDate) ?: return@forEach
                val diffDays = ((returnDate.time - todayDate.time) / (1000 * 60 * 60 * 24)).toInt()

                val message = when (diffDays) {
                    2 -> "Buku \"${book.title}\" harus dikembalikan 2 hari lagi!"
                    1 -> "Buku \"${book.title}\" harus dikembalikan besok!"
                    0 -> "Buku \"${book.title}\" harus dikembalikan HARI INI!"
                    else -> null
                }

                message?.let {
                    NotificationHelper.send(
                        context = applicationContext,
                        notifId = book.id.hashCode(),
                        title = "⏰ Pengingat Pinbook",
                        message = it
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return Result.success()
    }
}