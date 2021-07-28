package com.example.earthquakes.presentation.map.interactors

import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import javax.inject.Inject

data class MapInteractors @Inject constructor(val useCaseGetLocalQuakes: UseCaseGetLocalQuakes)
