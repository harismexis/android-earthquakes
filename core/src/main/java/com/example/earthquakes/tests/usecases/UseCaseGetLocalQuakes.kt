package com.example.earthquakes.tests.usecases

import com.example.earthquakes.data.QuakeLocalRepository

class UseCaseGetLocalQuakes(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke() = repository.getQuakes()
}
