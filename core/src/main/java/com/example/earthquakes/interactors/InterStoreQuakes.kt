package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.domain.Quake

class InterStoreQuakes(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke(items: List<Quake>) = repository.insertQuakes(items)
}
