package com.example.earthquakes.domain

data class Quake(
    val id: String,
    val datetime: String?,
    val depth: Float?,
    val longitude: Float?,
    val latitude: Float?,
    val source: String?,
    val magnitude: Float?,
)

fun Quake.getInfo(): String {
    return "M: $magnitude D: $depth"
}
