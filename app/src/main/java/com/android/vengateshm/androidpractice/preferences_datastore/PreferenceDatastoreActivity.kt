package com.android.vengateshm.androidpractice.preferences_datastore

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.android.vengateshm.androidpractice.R
import kotlinx.coroutines.launch

class PreferenceDatastoreActivity : AppCompatActivity() {

    private lateinit var clRoot: ConstraintLayout
    private lateinit var tvThemeText: TextView
    private lateinit var btnChangeTheme: Button

    private lateinit var appPreferenceRepository: AppPreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_datastore)

        appPreferenceRepository = AppPreferenceRepository(this.applicationContext.appPrefDatastore)

        clRoot = findViewById(R.id.clRoot)
        tvThemeText = findViewById(R.id.tvThemeText)
        btnChangeTheme = findViewById(R.id.btnChangeTheme)

        lifecycleScope.launch {
            val theme = appPrefDatastore.isDarkTheme() ?: false
            changeTheme(theme)
        }

        btnChangeTheme.setOnClickListener {
            lifecycleScope.launch {
                val theme = appPrefDatastore.isDarkTheme() ?: false
                val newTheme = theme.not()
                appPreferenceRepository.setTheme(newTheme)
                changeTheme(newTheme)
            }
        }
    }

    private fun changeTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            tvThemeText.text = "Dark Theme"
            clRoot.setBackgroundColor(Color.BLACK)
            tvThemeText.setTextColor(Color.WHITE)
        } else {
            tvThemeText.text = "Light Theme"
            clRoot.setBackgroundColor(Color.WHITE)
            tvThemeText.setTextColor(Color.BLACK)
        }
    }
}