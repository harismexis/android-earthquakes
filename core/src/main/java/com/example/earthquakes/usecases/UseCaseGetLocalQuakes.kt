package com.example.earthquakes.usecases

import com.example.earthquakes.data.QuakeLocalRepository

class UseCaseGetLocalQuakes(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke() = repository.getQuakes()
}
