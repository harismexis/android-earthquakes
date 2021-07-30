package com.example.earthquakes.framework.di

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.framework.data.database.QuakeLocalDataSource
import com.example.earthquakes.framework.data.network.datasource.QuakeRemoteDataSource
import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.usecases.UseCaseStoreQuakes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseGetLocalQuakes(
        dataSource: QuakeLocalDataSource
    ): UseCaseGetLocalQuakes {
        return UseCaseGetLocalQuakes(QuakeLocalRepository(dataSource))
    }

    @Provides
    @Singleton
    fun provideUseCaseGetRemoteQuakes(
        dataSource: QuakeRemoteDataSource
    ): UseCaseGetRemoteQuakes {
        return UseCaseGetRemoteQuakes(QuakeRemoteRepository(dataSource))
    }

    @Provides
    @Singleton
    fun provideUseCaseStoreQuakes(
        dataSource: QuakeLocalDataSource
    ): UseCaseStoreQuakes {
        return UseCaseStoreQuakes(QuakeLocalRepository(dataSource))
    }

}