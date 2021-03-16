package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.home.setup.HomeViewModelTestSetup
import com.example.earthquakes.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class InterGetRemoteQuakesTest : UnitTestSetup() {

    companion object {
        const val north = 44.1f
        const val south = -9.9f
        const val east = -22.4f
        const val west = 55.2f
        const val username = "testuser"
    }

    @Mock
    private lateinit var mockRepository: QuakeRemoteRepository

    private lateinit var mockItems: List<Quake>
    private lateinit var subject: InterGetRemoteQuakes

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        subject = InterGetRemoteQuakes(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getQuakes(
                north, south, east, west, username
            )).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject.invoke(north, south, east, west, username)

            // then
            verify(mockRepository, times(1)).getQuakes(north, south, east, west, username)
            Assert.assertEquals(mockItems.size, items.size)
        }

}