package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeLocalRepository

class InterGetLocalQuake(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke(itemId: String) = repository.getQuake(itemId)
}
