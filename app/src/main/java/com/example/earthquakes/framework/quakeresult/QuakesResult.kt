package com.example.earthquakes.framework.quakeresult

import com.example.earthquakes.domain.Quake

sealed class QuakesResult {
    data class QuakesSuccess(val items: List<Quake>): QuakesResult()
    data class QuakesError(val error: String): QuakesResult()
}