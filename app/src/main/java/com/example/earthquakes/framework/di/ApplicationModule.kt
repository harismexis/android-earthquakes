package com.example.earthquakes.framework.di

import com.example.earthquakes.framework.data.database.dao.QuakeLocalDao
import com.example.earthquakes.framework.data.database.db.QuakeDatabase
import com.example.earthquakes.framework.application.MainApplication
import com.example.earthquakes.framework.util.resource.ResourceProvider
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.presentation.screens.preferences.PrefsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideLocalDao(app: MainApplication): QuakeLocalDao {
        return QuakeDatabase.getDatabase(app.applicationContext).getDao()
    }

    @Provides
    fun provideResourceProvider(app: MainApplication): ResourceProvider {
        return ResourceProvider(app.applicationContext)
    }

    @Provides
    fun providePrefsManager(app: MainApplication): PrefsManager {
        return PrefsManager(app.applicationContext)
    }

    @Provides
    fun provideConnectivityMonitor(app: MainApplication): ConnectivityMonitor {
        return ConnectivityMonitor(app.applicationContext)
    }

}