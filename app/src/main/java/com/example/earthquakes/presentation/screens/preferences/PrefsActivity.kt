package com.example.earthquakes.presentation.screens.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquakes.R
import com.example.earthquakes.databinding.ActivityPrefsBinding

class PrefsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrefsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        addPrefsScreen()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun addPrefsScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, PrefsFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}