package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

class QuakeLocalRepository(
    private val dataSource: IQuakeLocalDataSource
) {
    suspend fun storeQuakes(items: List<Quake>) = dataSource.storeQuakes(items)

    suspend fun getQuake(itemId: String): Quake? = dataSource.getQuake(itemId)

    suspend fun getQuakes(): List<Quake> = dataSource.getQuakes()
}