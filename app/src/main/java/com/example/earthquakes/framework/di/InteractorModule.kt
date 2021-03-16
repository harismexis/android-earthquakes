package com.example.earthquakes.framework.di

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.framework.datasource.database.QuakeLocalDataSource
import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDataSource
import com.example.earthquakes.interactors.InterGetLocalQuakes
import com.example.earthquakes.interactors.InterGetRemoteQuakes
import com.example.earthquakes.interactors.InterStoreQuakes
import com.example.earthquakes.presentation.interactors.HomeInteractors
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideHomeInteractors(
        interGetLocalQuakes: InterGetLocalQuakes,
        interGetRemoteQuakes: InterGetRemoteQuakes,
        interStoreQuakes: InterStoreQuakes
    ): HomeInteractors {
        return HomeInteractors(
            interGetLocalQuakes,
            interGetRemoteQuakes,
            interStoreQuakes
        )
    }

    @Provides
    fun provideInteractorGetLocalQuakes(
        dataSource: QuakeLocalDataSource
    ): InterGetLocalQuakes {
        return InterGetLocalQuakes(QuakeLocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetRemoteQuakes(
        dataSource: QuakeRemoteDataSource
    ): InterGetRemoteQuakes {
        return InterGetRemoteQuakes(QuakeRemoteRepository(dataSource))
    }

    @Provides
    fun provideInteractorStoreQuakes(
        dataSource: QuakeLocalDataSource
    ): InterStoreQuakes {
        return InterStoreQuakes(QuakeLocalRepository(dataSource))
    }

}