package com.example.earthquakes.framework.di

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.framework.datasource.database.QuakeLocalDataSource
import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDataSource
import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.usecases.UseCaseStoreQuakes
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideUseCaseGetLocalQuakes(
        dataSource: QuakeLocalDataSource
    ): UseCaseGetLocalQuakes {
        return UseCaseGetLocalQuakes(QuakeLocalRepository(dataSource))
    }

    @Provides
    fun provideUseCaseGetRemoteQuakes(
        dataSource: QuakeRemoteDataSource
    ): UseCaseGetRemoteQuakes {
        return UseCaseGetRemoteQuakes(QuakeRemoteRepository(dataSource))
    }

    @Provides
    fun provideUseCaseStoreQuakes(
        dataSource: QuakeLocalDataSource
    ): UseCaseStoreQuakes {
        return UseCaseStoreQuakes(QuakeLocalRepository(dataSource))
    }

}