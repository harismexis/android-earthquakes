package com.example.earthquakes.framework.di

import com.example.earthquakes.presentation.screens.home.ui.activity.HomeActivity
import com.example.earthquakes.presentation.screens.map.ui.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun mapsActivity(): MapsActivity

}
