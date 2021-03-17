package com.example.earthquakes.presentation.preferences

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.earthquakes.R
import com.example.earthquakes.framework.extensions.makeSignedDecimal
import com.example.earthquakes.framework.util.filter.InputFilterMinMax

class PrefsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        (findPreference("north") as EditTextPreference?).makeSignedDecimal()
        (findPreference("south") as EditTextPreference?).makeSignedDecimal()
        (findPreference("east") as EditTextPreference?).makeSignedDecimal()
        (findPreference("west") as EditTextPreference?).makeSignedDecimal()

        val maxResults = findPreference("max_quake_results") as EditTextPreference?
        maxResults?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            editText.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "500"))
        }
    }

}