package com.example.earthquakes.config.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.earthquakes.config.application.InstrumentedApplication

open class InstrumentedRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, InstrumentedApplication::class.java.name, context)
    }
}