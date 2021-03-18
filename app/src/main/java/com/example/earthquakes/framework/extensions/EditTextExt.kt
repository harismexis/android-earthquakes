package com.example.earthquakes.framework.extensions

import android.text.InputType
import android.widget.EditText

fun EditText?.makeSignedDecimal() {
    this?.inputType = InputType.TYPE_CLASS_NUMBER or
            InputType.TYPE_NUMBER_FLAG_DECIMAL or
            InputType.TYPE_NUMBER_FLAG_SIGNED
}

fun EditText?.makeNumeric() {
    this?.inputType = InputType.TYPE_CLASS_NUMBER
}

fun EditText?.makeMasked() {
    this?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
}