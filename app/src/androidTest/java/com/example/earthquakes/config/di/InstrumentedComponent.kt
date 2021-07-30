package com.example.earthquakes.config.di

import com.example.earthquakes.config.application.InstrumentedApplication
import com.example.earthquakes.config.factory.InstrumentedViewModelModule
import com.example.earthquakes.framework.di.ActivityBindingsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class,
        InstrumentedViewModelModule::class
    ]
)
interface InstrumentedComponent : AndroidInjector<InstrumentedApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedApplication)
                : InstrumentedComponent
    }

}