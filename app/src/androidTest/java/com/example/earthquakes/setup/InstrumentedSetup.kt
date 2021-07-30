package com.example.earthquakes.setup

import com.example.earthquakes.util.InstrumentedFileReader

open class InstrumentedSetup {

    protected val mockProvider = InstrumentedFileReader()

}