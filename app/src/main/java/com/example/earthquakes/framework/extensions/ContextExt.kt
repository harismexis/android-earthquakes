package com.example.earthquakes.framework.extensions

import android.content.Context
import androidx.annotation.StringRes

fun Context.getStringResAsFloat(@StringRes id: Int): Float? {
    return this.resources.getString(id).toFloatOrNull()
}

fun Context.getStringResAsInt(@StringRes id: Int): Int? {
    return this.resources.getString(id).toIntOrNull()
}