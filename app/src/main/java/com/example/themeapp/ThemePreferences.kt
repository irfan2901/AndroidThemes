package com.example.themeapp

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemePreferences {

    private val PREFS_NAME = "theme_prefs"
    private val THEME_KEY = "theme_key"

    fun saveThemePreference(context: Context, theme: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(THEME_KEY, theme).apply()
    }

    fun getThemePreference(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

}