package com.example.earthquakes.usecases

import com.example.earthquakes.data.QuakeLocalRepository

class UseCaseGetLocalQuake(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke(itemId: String) = repository.getQuake(itemId)
}
