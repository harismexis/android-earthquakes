package com.example.earthquakes.datasource

import com.example.earthquakes.framework.data.database.QuakeLocalDao
import com.example.earthquakes.framework.data.database.QuakeLocalDataSource
import com.example.earthquakes.framework.extensions.toLocalItems
import com.example.earthquakes.framework.extensions.toLocalItem
import com.example.earthquakes.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class QuakeLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: QuakeLocalDao

    private lateinit var subject: QuakeLocalDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = QuakeLocalDataSource(mockDao)
    }

    @Test
    fun dataSourceInsertsItems_then_daoInsertsExpectedLocalItems() {
        runBlocking {
            // given
            val mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
            val mockLocalItems = mockItems.toLocalItems()

            // when
            subject.storeQuakes(mockItems)

            // then
            verify(mockDao, times(1)).insertItems(mockLocalItems)
        }
    }

    @Test
    fun dataSourceRequestsItem_then_daoRetrievesExpectedLocalItem() {
        runBlocking {
            // given
            val mockLocalItem = mockParser.getMockQuake().toLocalItem()
            val mockItemId = mockLocalItem.id
            Mockito.`when`(mockDao.getItemById(mockItemId)).thenReturn(mockLocalItem)

            // when
            val item = subject.getQuake(mockItemId)

            // then
            verify(mockDao, times(1)).getItemById(mockItemId)
        }
    }

    @Test
    fun dataSourceRequestsItems_then_daoRetrievesExpectedLocalItems() {
        runBlocking {
            // given
            val mockLocalItems = mockParser.getMockLocalQuakesFromFeedWithAllItemsValid()
            Mockito.`when`(mockDao.getAllItems()).thenReturn(mockLocalItems)

            // when
            val items = subject.getQuakes()

            // then
            verify(mockDao, times(1)).getAllItems()
        }
    }

}