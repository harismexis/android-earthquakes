package com.example.earthquakes.framework.datasource.network.api

import com.example.earthquakes.framework.datasource.network.model.QuakeFeed
import retrofit2.http.*

interface EarthquakeApi {

    @GET("earthquakesJSON")
    suspend fun getEarthquakes(
        @Query("north") north: Float,
        @Query("south") south: Float,
        @Query("east") east: Float,
        @Query("west") west: Float,
        @Query("maxRows") maxRows: Int,
        @Query("username") username: String
    ): QuakeFeed?

}