package com.example.earthquakes.setup.di.module

import android.content.Context
import com.example.earthquakes.setup.application.InstrumentedMainApplication
import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): QuakeLocalDao {
        return QuakeDatabase.getDatabase(app.applicationContext).getDao()
    }


}