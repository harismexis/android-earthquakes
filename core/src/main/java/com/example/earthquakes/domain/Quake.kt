package com.example.earthquakes.domain

import java.io.Serializable

data class Quake(
    val id: String,
    val datetime: String?,
    val depth: Float?,
    val longitude: Float?,
    val latitude: Float?,
    val source: String?,
    val magnitude: Float?,
) : Serializable