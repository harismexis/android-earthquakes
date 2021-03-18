package com.example.earthquakes.framework.di

import android.content.Context
import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import com.example.earthquakes.framework.application.MainApplication
import com.example.earthquakes.framework.resource.ResourceProvider
import com.example.earthquakes.presentation.preferences.PrefsManager
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: Context): QuakeLocalDao {
        return QuakeDatabase.getDatabase(app.applicationContext).getDao()
    }

    @Provides
    fun provideResourceProvider(app: Context): ResourceProvider {
        return ResourceProvider(app.applicationContext)
    }

    @Provides
    fun providePrefsManager(app: Context): PrefsManager {
        return PrefsManager(app.applicationContext)
    }

}