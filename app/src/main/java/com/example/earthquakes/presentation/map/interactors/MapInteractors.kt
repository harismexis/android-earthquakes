package com.example.earthquakes.presentation.map.interactors

import com.example.earthquakes.interactors.InterGetLocalQuakes
import javax.inject.Inject

data class MapInteractors @Inject constructor(val interGetLocalQuakes: InterGetLocalQuakes)
