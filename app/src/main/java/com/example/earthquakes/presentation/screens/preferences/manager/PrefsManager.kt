package com.example.earthquakes.presentation.screens.preferences.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.earthquakes.R
import com.example.earthquakes.framework.extensions.getStringResAsFloat
import com.example.earthquakes.framework.extensions.getStringResAsInt
import javax.inject.Singleton

@Singleton
class PrefsManager(context: Context) {

    private var appContext = context.applicationContext

    private val keyNorthBound = appContext.getString(R.string.key_pref_north_bound)
    private val keySouthBound = appContext.getString(R.string.key_pref_south_bound)
    private val keyEastBound = appContext.getString(R.string.key_pref_east_bound)
    private val keyWestBound = appContext.getString(R.string.key_pref_west_bound)
    private val keyMaxQuakes = appContext.getString(R.string.key_pref_max_quakes)
    private val keyUsername = appContext.getString(R.string.key_pref_username)

    private val northDefault: Float =
        appContext.getStringResAsFloat(R.string.pref_north_default) ?: 44.1f
    private val southDefault: Float =
        appContext.getStringResAsFloat(R.string.pref_south_default) ?: -9.9f
    private val eastDefault: Float =
        appContext.getStringResAsFloat(R.string.pref_east_default) ?: -22.4f
    private val westDefault: Float =
        appContext.getStringResAsFloat(R.string.pref_west_default) ?: 55.2f
    private val maxQuakesDefault: Int =
        appContext.getStringResAsInt(R.string.pref_max_results_default) ?: 10
    private val userNameDefault: String =
        appContext.getString(R.string.pref_username_default)

    companion object {
        private const val MAX_QUAKES_THRESHOLD = 500
    }

    fun getNorth(): Float {
        val north = getStringPref(keyNorthBound)?.toFloatOrNull()
        north?.let {
            return it
        }
        return northDefault
    }

    fun getSouth(): Float {
        val south = getStringPref(keySouthBound)?.toFloatOrNull()
        south?.let {
            return it
        }
        return southDefault
    }

    fun getEast(): Float {
        val east = getStringPref(keyEastBound)?.toFloatOrNull()
        east?.let {
            return it
        }
        return eastDefault
    }

    fun getWest(): Float {
        val west = getStringPref(keyWestBound)?.toFloatOrNull()
        west?.let {
            return it
        }
        return westDefault
    }

    fun getMaxQuakeResults(): Int {
        val maxResults = getStringPref(keyMaxQuakes)?.toIntOrNull()
        maxResults?.let {
            return if (maxResults <= MAX_QUAKES_THRESHOLD) it else MAX_QUAKES_THRESHOLD
        }
        return maxQuakesDefault
    }

    fun getUsername(): String {
        val username = getStringPref(keyUsername)
        return if (!username.isNullOrBlank()) username else userNameDefault
    }

    private fun getStringPref(
        key: String,
    ): String? {
        return getSharedPreferences().getString(key, null)
    }

    private fun getSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }
}