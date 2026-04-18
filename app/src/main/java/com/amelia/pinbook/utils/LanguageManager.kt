package com.amelia.pinbook.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale

object LanguageManager {

    private const val PREF_NAME = "PinBookLanguage"
    private const val KEY_LANGUAGE = "selected_language"

    /**
     * Map koordinat ke negara (Simple Range Detection)
     */
    fun getCountryFromCoordinates(lat: Double, lng: Double): String {
        return when {
            // Indonesia
            lat in -11.0..-5.0 && lng in 95.0..141.0 -> "ID"

            // United Kingdom
            lat in 49.0..61.0 && lng in -8.0..2.0 -> "GB"

            // United States
            lat in 24.0..50.0 && lng in -125.0..-66.0 -> "US"

            // Australia
            lat in -44.0..-10.0 && lng in 112.0..154.0 -> "AU"

            // Canada
            lat in 41.0..84.0 && lng in -141.0..-52.0 -> "CA"

            // Japan
            lat in 24.0..46.0 && lng in 122.0..154.0 -> "JP"

            // France
            lat in 41.0..51.0 && lng in -5.0..10.0 -> "FR"

            // Germany
            lat in 47.0..55.0 && lng in 5.0..15.0 -> "DE"

            // Spain
            lat in 36.0..44.0 && lng in -9.0..4.0 -> "ES"

            // Italy
            lat in 36.0..47.0 && lng in 6.0..19.0 -> "IT"

            // South Korea
            lat in 33.0..39.0 && lng in 124.0..132.0 -> "KR"

            // China
            lat in 18.0..54.0 && lng in 73.0..135.0 -> "CN"

            else -> "US" // Default ke US
        }
    }

    /**
     * Map kode negara ke kode bahasa
     */
    fun getLanguageFromCountry(countryCode: String): String {
        return when (countryCode) {
            "GB", "US", "AU", "CA" -> "en" // English
            "ID" -> "in" // Indonesia
            "JP" -> "ja" // Japanese
            "FR" -> "fr" // French
            "DE" -> "de" // German
            "ES" -> "es" // Spanish
            "IT" -> "it" // Italian
            "KR" -> "ko" // Korean
            "CN" -> "zh" // Chinese
            else -> "en" // Default English
        }
    }

    /**
     * Get nama negara untuk dialog
     */
    fun getCountryName(countryCode: String): String {
        return when (countryCode) {
            "GB" -> "United Kingdom"
            "US" -> "United States"
            "AU" -> "Australia"
            "CA" -> "Canada"
            "ID" -> "Indonesia"
            "JP" -> "Japan"
            "FR" -> "France"
            "DE" -> "Germany"
            "ES" -> "Spain"
            "IT" -> "Italy"
            "KR" -> "South Korea"
            "CN" -> "China"
            else -> "International"
        }
    }

    /**
     * Get nama bahasa untuk dialog
     */
    fun getLanguageName(languageCode: String): String {
        return when (languageCode) {
            "en" -> "English"
            "in" -> "Bahasa Indonesia"
            "ja" -> "日本語 (Japanese)"
            "fr" -> "Français (French)"
            "de" -> "Deutsch (German)"
            "es" -> "Español (Spanish)"
            "it" -> "Italiano (Italian)"
            "ko" -> "한국어 (Korean)"
            "zh" -> "中文 (Chinese)"
            else -> "English"
        }
    }

    /**
     * Simpan bahasa pilihan user
     */
    fun saveLanguage(context: Context, languageCode: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()
    }

    /**
     * Ambil bahasa yang tersimpan (default: Indonesia)
     */
    fun getLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, "in") ?: "in" // Default Bahasa Indonesia
    }

    /**
     * Set locale/bahasa untuk context
     */
    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }

    /**
     * Update bahasa dan restart activity
     */
    fun changeLanguage(context: Context, languageCode: String) {
        saveLanguage(context, languageCode)

        // Restart activity untuk apply bahasa baru
        if (context is android.app.Activity) {
            context.recreate()
        }
    }
}