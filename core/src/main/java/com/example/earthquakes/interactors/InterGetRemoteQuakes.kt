package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeRemoteRepository

class InterGetRemoteQuakes(
    private val repository: QuakeRemoteRepository
) {
    suspend operator fun invoke(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        username: String
    ) = repository.getQuakes(north, south, east, west, username)
}
