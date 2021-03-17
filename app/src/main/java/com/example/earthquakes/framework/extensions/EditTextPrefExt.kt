package com.example.earthquakes.framework.extensions

import android.text.InputType
import androidx.preference.EditTextPreference

fun EditTextPreference?.makeSignedDecimal() {
    this?.setOnBindEditTextListener { editText ->
        editText.inputType = InputType.TYPE_CLASS_NUMBER or
                InputType.TYPE_NUMBER_FLAG_DECIMAL or
                InputType.TYPE_NUMBER_FLAG_SIGNED
    }
}

fun EditTextPreference?.makePositiveNumeric() {
    this?.setOnBindEditTextListener { editText ->
        editText.inputType = InputType.TYPE_CLASS_NUMBER
    }
}