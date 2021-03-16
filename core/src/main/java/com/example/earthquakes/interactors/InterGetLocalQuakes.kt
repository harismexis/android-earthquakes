package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeLocalRepository

class InterGetLocalQuakes(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke() = repository.getQuakes()
}
