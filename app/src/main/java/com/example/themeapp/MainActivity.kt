package com.example.themeapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var selectedThemeMode: Int = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val changeThemeButton = findViewById<Button>(R.id.changeThemeButton)

        changeThemeButton.setOnClickListener {
            showThemeSelectionDialog()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showThemeSelectionDialog() {
        val currentTheme = ThemePreferences.getThemePreference(this)
        val options = arrayOf("System Default", "Dark", "Light")

        selectedThemeMode = currentTheme
        val checkedItem = when (currentTheme) {
            AppCompatDelegate.MODE_NIGHT_YES -> 1
            AppCompatDelegate.MODE_NIGHT_NO -> 2
            else -> 0
        }

        AlertDialog.Builder(this)
            .setTitle("Select Theme")
            .setSingleChoiceItems(options, checkedItem) { _, which ->
                selectedThemeMode = when (which) {
                    1 -> AppCompatDelegate.MODE_NIGHT_YES
                    2 -> AppCompatDelegate.MODE_NIGHT_NO
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            }
            .setPositiveButton("Ok") { dialog, _ ->
                ThemePreferences.saveThemePreference(this, selectedThemeMode)
                AppCompatDelegate.setDefaultNightMode(selectedThemeMode)
                recreate()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}