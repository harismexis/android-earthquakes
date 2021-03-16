package com.example.earthquakes.framework.di

import android.content.Context
import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import com.example.earthquakes.framework.application.MainApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): QuakeLocalDao {
        return QuakeDatabase.getDatabase(app.applicationContext).getDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}