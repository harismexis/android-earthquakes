package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

class QuakeRemoteRepository(
    private val dataSource: IQuakeRemoteDataSource
) {
    suspend fun getQuakes(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ): List<Quake> = dataSource.getQuakes(north, south, east, west, maxResults, username)
}