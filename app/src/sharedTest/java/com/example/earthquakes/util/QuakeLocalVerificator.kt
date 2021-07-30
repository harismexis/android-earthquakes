package com.example.earthquakes.util

import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.database.table.LocalQuake
import org.junit.Assert

class QuakeLocalVerificator {

    fun verifyLocalItemsAgainstItems(
        actual: List<LocalQuake>,
        expected: List<Quake>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalItemAgainstItem(localItem, item)
        }
    }

    private fun verifyLocalItemAgainstItem(
        actual: LocalQuake,
        expected: Quake
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.datetime, actual.datetime)
        Assert.assertEquals(expected.depth, actual.depth)
        Assert.assertEquals(expected.longitude, actual.longitude)
        Assert.assertEquals(expected.latitude, actual.latitude)
        Assert.assertEquals(expected.source, actual.source)
        Assert.assertEquals(expected.magnitude, actual.magnitude)
    }

    fun verifyItemsAgainstLocalItems(
        actual: List<Quake>,
        expected: List<LocalQuake>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, localItem ->
            val item = actual[index]
            verifyItemAgainstLocalItem(item, localItem)
        }
    }

    private fun verifyItemAgainstLocalItem(
        actual: Quake,
        expected: LocalQuake
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.datetime, actual.datetime)
        Assert.assertEquals(expected.depth, actual.depth)
        Assert.assertEquals(expected.longitude, actual.longitude)
        Assert.assertEquals(expected.latitude, actual.latitude)
        Assert.assertEquals(expected.source, actual.source)
        Assert.assertEquals(expected.magnitude, actual.magnitude)
    }

}

