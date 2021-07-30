package com.example.earthquakes.extensions

import com.example.earthquakes.framework.extensions.toItems
import com.example.earthquakes.framework.extensions.toLocalItems
import com.example.earthquakes.setup.UnitTestSetup
import com.example.earthquakes.util.QuakeLocalVerificator
import com.example.earthquakes.util.verifyListSizeWhenAllIdsValid
import com.example.earthquakes.util.verifyListsHaveSameSize
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QuakeLocalExtTest : UnitTestSetup() {

    private val verificator = QuakeLocalVerificator()

    @Test
    fun itemsAreConvertedToLocalItems_then_localItemsListIsTheExpected() {
        // given
        val items = mockParser.getMockQuakesFromFeedWithAllItemsValid()

        // when
        val localItems = items.toLocalItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(items)
        verifyListSizeWhenAllIdsValid(localItems)
        verificator.verifyLocalItemsAgainstItems(localItems, items)
    }

    @Test
    fun localItemsAreConvertedToItems_then_itemListIsTheExpected() {
        // given
        val localItems = mockParser.getMockLocalQuakesFromFeedWithAllItemsValid()

        // when
        val items = localItems.toItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(localItems)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyItemsAgainstLocalItems(items, localItems)
    }

    override fun initialiseClassUnderTest() {
        // do nothing
    }

}
