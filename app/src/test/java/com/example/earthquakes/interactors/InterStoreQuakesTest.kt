package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class InterStoreQuakesTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: QuakeLocalRepository

    private lateinit var mockItems: List<Quake>
    private lateinit var subject: InterStoreQuakes

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = InterStoreQuakes(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            subject.invoke(mockItems)

            // then
            verify(mockRepository, times(1)).storeQuakes(mockItems)
        }

}