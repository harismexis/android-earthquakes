package com.example.earthquakes.framework.data.network.datasource

import com.example.earthquakes.data.IQuakeRemoteDataSource
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.network.api.EarthquakeApi
import com.example.earthquakes.framework.extensions.toItems
import javax.inject.Inject
import javax.inject.Singleton

class QuakeRemoteDataSource @Inject constructor(
    private val api: EarthquakeApi
) : IQuakeRemoteDataSource {

    override suspend fun getQuakes(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ): List<Quake> {
        return api.getEarthquakes(north, south, east, west, maxResults, username).toItems()
    }

}