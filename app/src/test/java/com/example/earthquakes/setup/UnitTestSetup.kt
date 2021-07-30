package com.example.earthquakes.setup

import com.example.earthquakes.util.UnitTestFileReader
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestFileReader()

    protected val mockNorthBound = 44.1f
    protected val mockSouthBound = -9.9f
    protected val mockEastBound = -22.4f
    protected val mockWestBound = 55.2f
    protected val mockMaxResults = 10
    protected val mockUsername = "testuser"

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}