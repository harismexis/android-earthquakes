package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

data class QuakeRemoteRepository(
    private val dataSource: QuakeBaseRemoteDataSource
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