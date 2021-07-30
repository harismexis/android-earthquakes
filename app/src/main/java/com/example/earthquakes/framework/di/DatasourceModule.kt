package com.example.earthquakes.framework.di

import com.example.earthquakes.data.IQuakeLocalDataSource
import com.example.earthquakes.data.IQuakeRemoteDataSource
import com.example.earthquakes.framework.data.database.QuakeLocalDao
import com.example.earthquakes.framework.data.database.QuakeLocalDataSource
import com.example.earthquakes.framework.data.network.api.EarthquakeApi
import com.example.earthquakes.framework.data.network.datasource.QuakeRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatasourceModule {

    @Provides
    @Singleton
    fun provideIQuakeLocalDataSource(
        dao: QuakeLocalDao
    ): IQuakeLocalDataSource {
        return QuakeLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideIQuakeRemoteDataSource(
        api: EarthquakeApi
    ): IQuakeRemoteDataSource {
        return QuakeRemoteDataSource(api)
    }

}