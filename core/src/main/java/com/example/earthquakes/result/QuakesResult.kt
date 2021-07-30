package com.example.earthquakes.result

import com.example.earthquakes.domain.Quake

sealed class QuakesResult {
    data class Success(val items: List<Quake>): QuakesResult()
    data class Error(val error: String): QuakesResult()
}