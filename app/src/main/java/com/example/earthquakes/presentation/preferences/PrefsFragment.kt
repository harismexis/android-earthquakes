package com.example.earthquakes.presentation.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.earthquakes.R

class PrefsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}