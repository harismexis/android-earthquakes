package com.example.earthquakes.setup.di.module

import android.content.Context
import com.example.earthquakes.setup.application.InstrumentedApplication
import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedApplication): QuakeLocalDao {
        return QuakeDatabase.getDatabase(app.applicationContext).getDao()
    }


}