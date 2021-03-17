package com.example.earthquakes.presentation.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.earthquakes.R
import com.example.earthquakes.framework.extensions.getFloatRes
import com.example.earthquakes.framework.extensions.getIntRes

class PrefsManager(context: Context) {

    private var appContext = context.applicationContext

    private val keyNorthBound = appContext.getString(R.string.key_pref_north_bound)
    private val keySouthBound = appContext.getString(R.string.key_pref_south_bound)
    private val keyEastBound = appContext.getString(R.string.key_pref_east_bound)
    private val keyWestBound = appContext.getString(R.string.key_pref_west_bound)
    private val keyMaxQuakes = appContext.getString(R.string.key_pref_max_quakes)

    private val northDefault: Float = appContext.getFloatRes(R.dimen.pref_north_default)
    private val southDefault: Float = appContext.getFloatRes(R.dimen.pref_south_default)
    private val eastDefault: Float = appContext.getFloatRes(R.dimen.pref_east_default)
    private val westDefault: Float = appContext.getFloatRes(R.dimen.pref_west_default)
    private val maxQuakesDefault: Int = appContext.getIntRes(R.integer.pref_max_results_default)

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

    private fun getStringPref(
        key: String,
    ): String? {
        return getSharedPreferences().getString(key, null)
    }

    private fun getSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }
}