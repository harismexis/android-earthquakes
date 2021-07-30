package com.example.earthquakes.parser

import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.database.LocalQuake
import com.example.earthquakes.framework.data.network.model.QuakeFeed
import com.example.earthquakes.framework.extensions.toItems
import com.example.earthquakes.framework.extensions.toLocalItems
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

abstract class BaseMockParser {

    companion object {

        const val EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT = 3
        const val EXPECTED_NUM_QUAKES_WHEN_TWO_EMPTY = 3
        const val EXPECTED_NUM_QUAKES_WHEN_NO_DATA = 0

        private const val TEST_QUAKES_FILE_FIVE_VALID_ITEMS =
            "test_quake_data_five_valid_items.json"
        private const val TEST_QUAKES_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT =
            "test_quake_data_five_items_but_two_ids_absent.json"
        private const val TEST_QUAKES_FILE_FIVE_ITEMS_BUT_TWO_EMPTY =
            "test_quake_data_five_items_but_two_items_empty.json"
        private const val TEST_QUAKES_FILE_FIVE_ITEMS_ALL_IDS_ABSENT =
            "test_quake_data_five_items_all_ids_absent.json"
        private const val TEST_QUAKES_FILE_EMPTY_JSON = "test_quake_data_empty_json.json"
    }

    abstract fun getFileAsString(filePath: String): String

    // get quake local items (database entities)

    fun getMockLocalQuakesFromFeedWithAllItemsValid(): List<LocalQuake> =
        getMockQuakeFeedAllIdsValid().toItems().toLocalItems()

    fun getMockLocalQuakesFromFeedWithSomeIdsAbsent(): List<LocalQuake> =
        getMockQuakeFeedSomeIdsAbsent().toItems().toLocalItems()

    fun getMockLocalQuakesFromFeedWithAllIdsAbsent(): List<LocalQuake> =
        getMockQuakeFeedAllIdsAbsent().toItems().toLocalItems()

    // get quake core items

    fun getMockQuake(): Quake = getMockQuakeFeedAllIdsValid().toItems()[0]

    fun getMockQuakesFromFeedWithAllItemsValid(): List<Quake> =
        getMockQuakeFeedAllIdsValid().toItems()

    fun getMockQuakesFromFeedWithSomeIdsAbsent(): List<Quake> =
        getMockQuakeFeedSomeIdsAbsent().toItems()

    fun getMockQuakesFromFeedWithSomeItemsEmpty(): List<Quake> =
        getMockQuakeFeedSomeItemsEmpty().toItems()

    fun getMockQuakesFromFeedWithAllIdsAbsent(): List<Quake> =
        getMockQuakeFeedAllIdsAbsent().toItems()

    fun getMockQuakesFromFeedWithEmptyJsonArray(): List<Quake> =
        getMockQuakeFeedEmptyJsonArray().toItems()

    // get json object

    fun getMockQuakeFeedAllIdsValid(): QuakeFeed =
        getMockQuakeFeed(getMockQuakeDataAllIdsValid())

    fun getMockQuakeFeedSomeIdsAbsent(): QuakeFeed =
        getMockQuakeFeed(getMockQuakeDataSomeIdsAbsent())

    fun getMockQuakeFeedSomeItemsEmpty(): QuakeFeed =
        getMockQuakeFeed(getMockQuakeDataSomeItemsEmpty())

    fun getMockQuakeFeedAllIdsAbsent(): QuakeFeed =
        getMockQuakeFeed(getMockQuakeDataAllIdsAbsent())

    fun getMockQuakeFeedEmptyJsonArray(): QuakeFeed =
        getMockQuakeFeed(mockQuakeDataEmptyJson())

    // get raw string json

    private fun getMockQuakeDataAllIdsValid(): String =
        getFileAsString(TEST_QUAKES_FILE_FIVE_VALID_ITEMS)

    private fun getMockQuakeDataSomeIdsAbsent(): String = getFileAsString(
        TEST_QUAKES_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT
    )

    private fun getMockQuakeDataSomeItemsEmpty(): String = getFileAsString(
        TEST_QUAKES_FILE_FIVE_ITEMS_BUT_TWO_EMPTY
    )

    private fun getMockQuakeDataAllIdsAbsent(): String = getFileAsString(
        TEST_QUAKES_FILE_FIVE_ITEMS_ALL_IDS_ABSENT
    )

    private fun mockQuakeDataEmptyJson(): String = getFileAsString(TEST_QUAKES_FILE_EMPTY_JSON)

    private fun getMockQuakeFeed(text: String): QuakeFeed {
        return convertToFeed(text)
    }

    private inline fun <reified T> convertToFeed(jsonString: String?): T {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, T::class.java)
    }

}