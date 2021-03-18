package com.example.earthquakes.framework.datasource.network.data

import com.example.earthquakes.framework.datasource.network.api.EarthquakeApi
import com.example.earthquakes.framework.datasource.network.model.QuakeFeed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuakeRemoteDao @Inject constructor(private val api: EarthquakeApi) {

    suspend fun getQuakeFeed(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ): QuakeFeed? {
        return api.getEarthquakes(north, south, east, west, maxResults, username)
    }

}