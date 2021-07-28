package com.example.earthquakes.datasource

import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDao
import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDataSource
import com.example.earthquakes.setup.UnitTestSetup
import com.example.earthquakes.utils.QuakeRemoteVerificator
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class QuakeRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: QuakeRemoteDao

    private var verificator = QuakeRemoteVerificator()

    private lateinit var subject: QuakeRemoteDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = QuakeRemoteDataSource(mockDao)
    }

    @Test
    fun dateSourceRequestsItems_then_daoRequestsItems() {
        // when
        runBlocking {
            // given
            val mockFeed = mockParser.getMockQuakeFeedAllIdsValid()
            Mockito.`when`(mockDao.getQuakeFeed(
                mockNorthBound,
                mockSouthBound,
                mockEastBound,
                mockWestBound,
                mockMaxResults,
                mockUsername
            )).thenReturn(mockFeed)

            // when
            val items = subject.getQuakes(
                mockNorthBound,
                mockSouthBound,
                mockEastBound,
                mockWestBound,
                mockMaxResults,
                mockUsername
            )

            // then
            verify(mockDao, times(1)).getQuakeFeed(
                mockNorthBound,
                mockSouthBound,
                mockEastBound,
                mockWestBound,
                mockMaxResults,
                mockUsername
            )
            verificator.verifyItemsAgainstRemoteFeed(items, mockFeed)
        }
    }

}