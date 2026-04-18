package com.amelia.pinbook

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// Biar notifikasi tetap jalan walau HP di-restart
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            BookReminderScheduler.schedule(context)
        }
    }
}