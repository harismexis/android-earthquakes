package com.example.earthquakes.framework.datasource.network.data

import com.example.earthquakes.data.QuakeBaseRemoteDataSource
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.extensions.toItems
import retrofit2.http.Query
import javax.inject.Inject

class QuakeRemoteDataSource @Inject constructor(
    private val dao: QuakeRemoteDao
) : QuakeBaseRemoteDataSource {

    override suspend fun getQuakes(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ): List<Quake> {
        return dao.getQuakeFeed(north, south, east, west, maxResults, username).toItems()
    }

}