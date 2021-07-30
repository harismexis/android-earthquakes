package com.example.earthquakes.framework.util.resource

import android.content.Context
import androidx.annotation.StringRes
import com.example.earthquakes.R
import javax.inject.Singleton

@Singleton
class ResourceProvider(context: Context) {

    private val appContext = context.applicationContext

    fun getString(@StringRes id: Int): String {
        return appContext.getString(id)
    }

    fun getUnauthorisedUserMessage(): String {
        return appContext.getString(R.string.unauthorised_user)
    }
}