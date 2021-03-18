package com.example.earthquakes.framework.di

import com.example.earthquakes.framework.base.BaseActivity
import com.example.earthquakes.presentation.home.ui.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

}
