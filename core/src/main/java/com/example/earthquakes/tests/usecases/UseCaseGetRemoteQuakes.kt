package com.example.earthquakes.tests.usecases

import com.example.earthquakes.data.QuakeRemoteRepository

class UseCaseGetRemoteQuakes(
    private val repository: QuakeRemoteRepository
) {
    suspend operator fun invoke(
        north: Float,
        south: Float,
        east: Float,
        west: Float,
        maxResults: Int,
        username: String
    ) = repository.getQuakes(north, south, east, west, maxResults, username)
}
