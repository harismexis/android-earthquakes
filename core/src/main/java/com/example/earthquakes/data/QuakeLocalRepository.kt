package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

class QuakeLocalRepository(
    private val dataSource: QuakeBaseLocalDataSource
) {
    suspend fun insertQuakes(items: List<Quake>) = dataSource.insert(items)

    suspend fun getQuake(itemId: String): Quake? = dataSource.getQuake(itemId)

    suspend fun getQuakes(): List<Quake> = dataSource.getQuakes()
}