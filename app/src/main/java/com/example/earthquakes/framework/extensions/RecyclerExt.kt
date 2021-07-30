package com.example.earthquakes.framework.extensions

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.presentation.widget.RecyclerDivider

fun RecyclerView.setDivider(
    @DrawableRes divider: Int
) {
    ContextCompat.getDrawable(this.context, divider)?.let {
        this.addItemDecoration(RecyclerDivider(it))
    }
}