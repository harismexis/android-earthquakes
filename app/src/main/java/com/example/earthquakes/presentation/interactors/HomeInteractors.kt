package com.example.earthquakes.presentation.interactors

import com.example.earthquakes.interactors.InterGetLocalQuakes
import com.example.earthquakes.interactors.InterGetRemoteQuakes
import com.example.earthquakes.interactors.InterStoreQuakes

data class HomeInteractors(
    val interGetLocalQuakes: InterGetLocalQuakes,
    val interGetRemoteQuakes: InterGetRemoteQuakes,
    val interStoreQuakes: InterStoreQuakes
)
