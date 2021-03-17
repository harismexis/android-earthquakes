package com.example.earthquakes.presentation.preferences

import android.os.Bundle
import android.text.InputFilter
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.earthquakes.R
import com.example.earthquakes.framework.extensions.*
import com.example.earthquakes.framework.util.filter.InputFilterMinMax

class PrefsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        setupBoundingBoxPref(R.string.key_pref_north_bound, R.dimen.pref_north_default)
        setupBoundingBoxPref(R.string.key_pref_south_bound, R.dimen.pref_south_default)
        setupBoundingBoxPref(R.string.key_pref_east_bound, R.dimen.pref_east_default)
        setupBoundingBoxPref(R.string.key_pref_west_bound, R.dimen.pref_west_default)

        val maxResults = findEditTextPrefByKey(R.string.key_pref_max_quakes)
        val maxResultsDefault = getIntResAsString(R.integer.pref_max_results_default)
        maxResults?.setOnBindEditTextListener { editText ->
            editText.makeNumeric()
            editText.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "500"))
            if (editText.text.isNullOrBlank()) editText.setText(maxResultsDefault)
        }
        maxResults?.setDefaultValue(maxResultsDefault)
    }

    private fun getFloatResAsString(@DimenRes id: Int): String {
        return requireContext().getFloatResAsString(id)
    }

    private fun getIntResAsString(@IntegerRes id: Int): String {
        return requireContext().getIntResAsString(id)
    }

    private fun findEditTextPrefByKey(@StringRes key: Int): EditTextPreference? {
        return findPreference(getString(key)) as EditTextPreference?
    }

    private fun setupBoundingBoxPref(
        @StringRes prefKeyResId: Int,
        @DimenRes defValueResId: Int
    ) {
        val editTextPref = findEditTextPrefByKey(prefKeyResId)
        val defaultValue = getFloatResAsString(defValueResId)
        editTextPref?.setOnBindEditTextListener { editText ->
            editText.makeSignedDecimal()
            if (editText.text.isNullOrBlank()) {
                editText.setText(defaultValue)
            }
        }
        editTextPref?.setDefaultValue(defaultValue)
    }
}