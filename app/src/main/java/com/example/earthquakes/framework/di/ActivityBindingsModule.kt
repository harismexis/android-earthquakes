package com.example.earthquakes.framework.di

import com.example.earthquakes.presentation.base.BaseActivity
import com.example.earthquakes.presentation.screens.home.ui.activity.HomeActivity
import com.example.earthquakes.presentation.screens.map.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun mapsActivity(): MapsActivity

}
