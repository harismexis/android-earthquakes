package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

interface QuakeBaseRemoteDataSource {

    suspend fun getQuakes(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        username: String
    ): List<Quake>

}