package com.example.earthquakes.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.earthquakes.framework.datasource.database.QuakeDatabase
import com.example.earthquakes.framework.datasource.database.QuakeLocalDao
import com.example.earthquakes.framework.datasource.database.LocalQuake
import com.example.earthquakes.setup.base.InstrumentedSetup
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_NO_DATA
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QuakeLocalDaoTest: InstrumentedSetup() {

    private lateinit var dao: QuakeLocalDao
    private lateinit var database: QuakeDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, QuakeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithAllItemsValid_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockLocalQuakesFromFeedWithAllItemsValid()

        // when
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithSomeIdsAbsent_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockLocalQuakesFromFeedWithSomeIdsAbsent()

        // when
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_then_noItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockLocalQuakesFromFeedWithAllIdsAbsent()

        // when
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_QUAKES_WHEN_NO_DATA)
    }

    private fun verifyActualAgainstExpected(
        actual: List<LocalQuake?>,
        expected: List<LocalQuake>,
        expectedNumberOfItems: Int
    ) {
        Assert.assertNotNull(actual)
        Assert.assertNotNull(expected)
        Assert.assertEquals(expected.size, actual.size)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(expectedNumberOfItems, actual.size)
        Assert.assertEquals(expectedNumberOfItems, expected.size)
    }

}
