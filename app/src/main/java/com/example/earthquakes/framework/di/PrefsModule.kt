package com.example.earthquakes.framework.di

import com.example.earthquakes.framework.application.MainApplication
import com.example.earthquakes.presentation.preferences.PrefsManager
import dagger.Module
import dagger.Provides

@Module
class PrefsModule {

    @Provides
    fun providePrefsManager(
        application: MainApplication
    ): PrefsManager {
        return PrefsManager(application)
    }
}