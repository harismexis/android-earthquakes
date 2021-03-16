package com.example.earthquakes.setup.di.component

import com.example.earthquakes.setup.application.InstrumentedMainApplication
import com.example.earthquakes.setup.di.module.InstrumentedApplicationModule
import com.example.earthquakes.setup.viewmodel.factory.InstrumentedViewModelModule
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
        InstrumentedViewModelModule::class,
        InstrumentedApplicationModule::class
    ]
)
interface InstrumentedMainComponent : AndroidInjector<InstrumentedMainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedMainApplication)
                : InstrumentedMainComponent
    }

}