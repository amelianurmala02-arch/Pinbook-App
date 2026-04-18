package com.amelia.pinbook.utils

import android.content.Context
import com.amelia.pinbook.R

/**
 * StringHelper - Helper untuk ambil teks sesuai bahasa
 */
object StringHelper {

    /**
     * Ambil string sesuai bahasa yang aktif
     *
     * @param context Context
     * @param key Key string (tanpa suffix bahasa)
     * @return String dalam bahasa yang aktif
     */
    fun getString(context: Context, key: String): String {
        val language = LanguageManager.getLanguage(context)
        val resourceName = "${key}_${language}"

        return try {
            val resourceId = context.resources.getIdentifier(
                resourceName,
                "string",
                context.packageName
            )

            if (resourceId != 0) {
                context.getString(resourceId)
            } else {
                // Fallback ke English kalau tidak ada
                val fallbackName = "${key}_en"
                val fallbackId = context.resources.getIdentifier(
                    fallbackName,
                    "string",
                    context.packageName
                )

                if (fallbackId != 0) {
                    context.getString(fallbackId)
                } else {
                    key // Return key kalau tidak ada terjemahan
                }
            }
        } catch (e: Exception) {
            key
        }
    }
}

/**
 * Extension function untuk Context
 * Supaya lebih mudah dipake
 */
fun Context.getLocalizedString(key: String): String {
    return StringHelper.getString(this, key)
}

