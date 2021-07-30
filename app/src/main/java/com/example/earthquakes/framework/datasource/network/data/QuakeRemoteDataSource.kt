package com.example.earthquakes.framework.datasource.network.data

import com.example.earthquakes.data.QuakeBaseRemoteDataSource
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.datasource.network.api.EarthquakeApi
import com.example.earthquakes.framework.datasource.network.model.QuakeFeed
import com.example.earthquakes.framework.extensions.toItems
import javax.inject.Inject

class QuakeRemoteDataSource @Inject constructor(
    private val api: EarthquakeApi
) : QuakeBaseRemoteDataSource {

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