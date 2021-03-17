package com.example.earthquakes.framework.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes

fun Context.getFloatRes(@DimenRes id: Int): Float {
    val outValue = TypedValue()
    this.resources.getValue(id, outValue, true)
    return outValue.float
}

fun Context.getFloatResAsString(@DimenRes id: Int): String {
    return this.getFloatRes(id).toString()
}

fun Context.getIntRes(@IntegerRes id: Int): Int {
    return this.resources.getInteger(id)
}

fun Context.getIntResAsString(@IntegerRes id: Int): String {
    return this.resources.getInteger(id).toString()
}