package com.example.earthquakes.presentation.preferences

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PrefsManager(private val application: Application) {

    companion object {
        private const val KEY_NORTH = "north"
        private const val KEY_SOUTH = "south"
        private const val KEY_EAST = "east"
        private const val KEY_WEST = "west"
        private const val KEY_MAX_QUAKE_RESULTS = "max_quake_results"

        private const val NORTH_DEFAULT = 44.1f
        private const val SOUTH_DEFAULT = -9.9f
        private const val EAST_DEFAULT = -22.4f
        private const val WEST_DEFAULT = 55.2f
        private const val MAX_QUAKE_RESULTS_DEFAULT = 10
    }

    fun getNorth(): Float {
        val north = getStringPref(KEY_NORTH)?.toFloatOrNull()
        north?.let {
            return it
        }
        return NORTH_DEFAULT
    }

    fun getSouth(): Float {
        val north = getStringPref(KEY_SOUTH)?.toFloatOrNull()
        north?.let {
            return it
        }
        return SOUTH_DEFAULT
    }

    fun getEast(): Float {
        val north = getStringPref(KEY_EAST)?.toFloatOrNull()
        north?.let {
            return it
        }
        return EAST_DEFAULT
    }

    fun getWest(): Float {
        val north = getStringPref(KEY_WEST)?.toFloatOrNull()
        north?.let {
            return it
        }
        return WEST_DEFAULT
    }

    fun getMaxQuakeResults(): Int {
        val maxResults = getStringPref(KEY_MAX_QUAKE_RESULTS)?.toIntOrNull()
        maxResults?.let {
            return if (maxResults <= 500) it else 500
        }
        return MAX_QUAKE_RESULTS_DEFAULT
    }

    private fun getStringPref(
        key: String,
    ): String? {
        return getSharedPreferences().getString(key, null)
    }

    private fun getSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
    }
}