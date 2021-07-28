package com.example.earthquakes.presentation.home.interactors

import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.usecases.UseCaseStoreQuakes

data class HomeUseCases(
    val getLocalQuakes: UseCaseGetLocalQuakes,
    val getRemoteQuakes: UseCaseGetRemoteQuakes,
    val storeQuakes: UseCaseStoreQuakes
)
