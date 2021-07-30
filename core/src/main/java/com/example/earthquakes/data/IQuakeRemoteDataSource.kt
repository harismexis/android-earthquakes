package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

interface IQuakeRemoteDataSource {

    suspend fun getQuakes(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ): List<Quake>

}