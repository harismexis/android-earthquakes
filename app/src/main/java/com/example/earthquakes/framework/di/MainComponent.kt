package com.example.earthquakes.framework.di

import com.example.earthquakes.framework.application.MainApplication
import com.example.earthquakes.framework.viewmodel.factory.ViewModelModule
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
        ViewModelModule::class,
        ApplicationModule::class,
        UseCaseModule::class,
        QuakeApiModule::class]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): MainComponent
    }

}