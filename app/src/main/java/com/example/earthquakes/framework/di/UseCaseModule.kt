package com.example.earthquakes.framework.di

import com.example.earthquakes.data.IQuakeLocalDataSource
import com.example.earthquakes.data.IQuakeRemoteDataSource
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
    fun useCaseGetLocalQuakes(
        dataSource: IQuakeLocalDataSource
    ): UseCaseGetLocalQuakes {
        return UseCaseGetLocalQuakes(QuakeLocalRepository(dataSource))
    }

    @Provides
    @Singleton
    fun useCaseGetRemoteQuakes(
        dataSource: IQuakeRemoteDataSource
    ): UseCaseGetRemoteQuakes {
        return UseCaseGetRemoteQuakes(QuakeRemoteRepository(dataSource))
    }

    @Provides
    @Singleton
    fun useCaseStoreQuakes(
        dataSource: IQuakeLocalDataSource
    ): UseCaseStoreQuakes {
        return UseCaseStoreQuakes(QuakeLocalRepository(dataSource))
    }

}