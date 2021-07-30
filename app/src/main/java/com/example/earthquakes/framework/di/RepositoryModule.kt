package com.example.earthquakes.framework.di

import com.example.earthquakes.data.IQuakeLocalDataSource
import com.example.earthquakes.data.IQuakeRemoteDataSource
import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.data.QuakeRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideQuakeLocalRepository(
        source: IQuakeLocalDataSource
    ): QuakeLocalRepository {
        return QuakeLocalRepository(source)
    }

    @Provides
    @Singleton
    fun provideQuakeRemoteRepository(
        source: IQuakeRemoteDataSource
    ): QuakeRemoteRepository {
        return QuakeRemoteRepository(source)
    }

}