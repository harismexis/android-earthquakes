package com.example.earthquakes.tests.usecases

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.domain.Quake

class UseCaseStoreQuakes(
    private val repository: QuakeLocalRepository
) {
    suspend operator fun invoke(items: List<Quake>) = repository.storeQuakes(items)
}
