package com.example.themeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val themeMode = ThemePreferences.getThemePreference(this)
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }

}