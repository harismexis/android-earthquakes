package com.example.earthquakes.usecases

import com.example.earthquakes.data.QuakeRemoteRepository
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class UseCaseGetRemoteQuakesTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: QuakeRemoteRepository

    private lateinit var mockItems: List<Quake>
    private lateinit var subject: UseCaseGetRemoteQuakes

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        subject = UseCaseGetRemoteQuakes(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(
                mockRepository.getQuakes(
                    mockNorthBound,
                    mockSouthBound,
                    mockEastBound,
                    mockWestBound,
                    mockMaxResults,
                    mockUsername
                )
            ).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject.invoke(
                mockNorthBound,
                mockSouthBound,
                mockEastBound,
                mockWestBound,
                mockMaxResults,
                mockUsername
            )

            // then
            verify(mockRepository, times(1)).getQuakes(
                mockNorthBound,
                mockSouthBound,
                mockEastBound,
                mockWestBound,
                mockMaxResults,
                mockUsername
            )
            Assert.assertEquals(mockItems.size, items.size)
        }

}