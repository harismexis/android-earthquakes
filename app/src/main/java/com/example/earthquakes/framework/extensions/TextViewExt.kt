package com.example.earthquakes.framework.extensions

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.earthquakes.R

fun TextView?.setTextOrDefault(value: String?, defaultValue: String) {
    if (this == null) return
    if (value.isNullOrBlank()) {
        this.text = defaultValue
        return
    }
    this.text = value
}

fun TextView?.setTextOrMissing(value: String?) {
    if (this == null) return
    this.setTextOrDefault(value, this.context.getString(R.string.missing_value))
}

fun TextView?.setTextColorCompat(@ColorRes color: Int) {
    if (this == null) return
    this.setTextColor(ContextCompat.getColor(this.context, color))
}
