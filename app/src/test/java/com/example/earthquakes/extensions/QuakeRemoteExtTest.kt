package com.example.earthquakes.extensions

import com.example.earthquakes.framework.extensions.toItems
import com.example.earthquakes.setup.UnitTestSetup
import com.example.earthquakes.utils.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QuakeRemoteExtTest : UnitTestSetup() {

    private val verificator = QuakeRemoteVerificator()

    @Test
    fun feedHasAllItemsValid_then_conversionToItemsIsCorrect() {
        // given
        val remoteFeed = mockParser.getMockQuakeFeedAllIdsValid()

        // when
        val items = remoteFeed.toItems()

        // then
        verifyListsHaveSameSize(remoteFeed.earthquakes!!, items)
        verifyListSizeWhenAllIdsValid(remoteFeed.earthquakes!!)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyItemsAgainstQuakesResponse(items, remoteFeed)
    }

    @Test
    fun feedHasSomeIdsAbsent_then_conversionToItemsIsCorrect() {
        // given
        val remoteFeed = mockParser.getMockQuakeFeedSomeIdsAbsent()

        // when
        val items = remoteFeed.toItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(items)
        verificator.verifyItemsAgainstQuakesResponse(items, remoteFeed)
    }

    @Test
    fun feedHasSomeEmptyItems_then_conversionToItemsIsCorrect() {
        // given
        val remoteFeed = mockParser.getMockQuakeFeedSomeItemsEmpty()

        // when
        val items = remoteFeed.toItems()

        // then
        verifyListSizeWhenSomeItemsEmpty(items)
        verificator.verifyItemsAgainstQuakesResponse(items, remoteFeed)
    }

    @Test
    fun feedHasAllIdsAbsent_then_itemListIsEmpty() {
        // given
        val remoteFeed = mockParser.getMockQuakeFeedAllIdsAbsent()

        // when
        val items = remoteFeed.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    @Test
    fun feedIsAnEmptyJson_then_itemListIsEmpty() {
        // given
        val remoteFeed = mockParser.getMockQuakeFeedEmptyJsonArray()

        // when
        val items = remoteFeed.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    override fun initialiseClassUnderTest() {
        // Do nothing
    }

}
