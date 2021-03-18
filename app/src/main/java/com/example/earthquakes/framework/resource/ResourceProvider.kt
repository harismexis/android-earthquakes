package com.example.earthquakes.framework.resource

import android.content.Context
import androidx.annotation.StringRes
import com.example.earthquakes.R

class ResourceProvider(private val context: Context) {

    private val appContext = context.applicationContext

    fun getString(@StringRes id: Int): String {
        return appContext.getString(id)
    }

    fun getUnauthorisedUserMessage(): String {
        return appContext.getString(R.string.unauthorised_user)
    }
}