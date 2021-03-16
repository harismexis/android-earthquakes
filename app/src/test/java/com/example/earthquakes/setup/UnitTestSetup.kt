package com.example.earthquakes.setup

import com.example.earthquakes.base.BaseTestSetup
import com.example.earthquakes.testutil.UnitTestMockParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}