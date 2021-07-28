package com.example.earthquakes.interactors

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.setup.UnitTestSetup
import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class UseCaseGetLocalQuakesTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: QuakeLocalRepository

    private lateinit var mockItems: List<Quake>
    private lateinit var subject: UseCaseGetLocalQuakes

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = UseCaseGetLocalQuakes(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getQuakes()).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject.invoke()

            // then
            verify(mockRepository, times(1)).getQuakes()
            Assert.assertEquals(mockItems.size, items.size)
        }

}