package com.example.earthquakes.framework.di

import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import com.example.earthquakes.framework.application.MainApplication
import com.example.earthquakes.framework.resource.ResourceProvider
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.presentation.preferences.PrefsManager
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
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