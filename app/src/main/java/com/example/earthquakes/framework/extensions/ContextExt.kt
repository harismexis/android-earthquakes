package com.example.earthquakes.framework.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.earthquakes.R

fun Context.getColor(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}
