package com.example.earthquakes.data

import com.example.earthquakes.domain.Quake

interface QuakeBaseLocalDataSource {

    suspend fun insert(items: List<Quake>)

    suspend fun getQuake(itemId: String): Quake?

    suspend fun getQuakes(): List<Quake>
}