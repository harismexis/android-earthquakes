package com.example.earthquakes.datasource

import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDao
import com.example.earthquakes.framework.datasource.network.data.QuakeRemoteDataSource
import com.example.earthquakes.setup.UnitTestSetup
import com.example.earthquakes.utils.QuakeRemoteVerificator
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class QuakeRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: QuakeRemoteDao

    private var verificator = QuakeRemoteVerificator()

    private lateinit var subject: QuakeRemoteDataSource

    companion object {
        const val north = 44.1f
        const val south = -9.9f
        const val east = -22.4f
        const val west = 55.2f
        const val username = "testuser"
    }

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
                north,
                south,
                east,
                west,
                username,
            )).thenReturn(mockFeed)

            // when
            val items = subject.getQuakes(north, south, east, west, username)

            // then
            verify(mockDao, times(1)).getQuakeFeed(north, south, east, west, username)
            verificator.verifyItemsAgainstRemoteFeed(items, mockFeed)
        }
    }

}