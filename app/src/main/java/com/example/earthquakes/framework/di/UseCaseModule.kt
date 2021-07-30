package com.example.earthquakes.framework.di

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.tests.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.tests.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.tests.usecases.UseCaseStoreQuakes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun useCaseGetLocalQuakes(
        repo: QuakeLocalRepository
    ): UseCaseGetLocalQuakes {
        return UseCaseGetLocalQuakes(repo)
    }

    @Provides
    @Singleton
    fun useCaseGetRemoteQuakes(
        repo: QuakeRemoteRepository
    ): UseCaseGetRemoteQuakes {
        return UseCaseGetRemoteQuakes(repo)
    }

    @Provides
    @Singleton
    fun useCaseStoreQuakes(
        repo: QuakeLocalRepository
    ): UseCaseStoreQuakes {
        return UseCaseStoreQuakes(repo)
    }

}