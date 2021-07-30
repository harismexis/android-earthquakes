package com.example.earthquakes.util

import com.example.earthquakes.parser.BaseFileReader
import org.junit.Assert

fun <T, P> verifyListsHaveSameSize(
    list0: List<T>,
    list1: List<P>
) {
    Assert.assertEquals(list0.size, list1.size)
}

fun <T> verifyListSize(
    expectedSize: Int,
    items: List<T>
) {
    Assert.assertEquals(expectedSize, items.size)
}

fun <T> verifyListSizeWhenAllIdsValid(items: List<T>) {
    verifyListSize(BaseFileReader.EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID, items)
}

fun <T> verifyListSizeWhenSomeIdsAbsent(items: List<T>) {
    verifyListSize(BaseFileReader.EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT, items)
}

fun <T> verifyListSizeWhenSomeItemsEmpty(items: List<T>) {
    verifyListSize(BaseFileReader.EXPECTED_NUM_QUAKES_WHEN_TWO_EMPTY, items)
}

fun <T> verifyListSizeForNoData(items: List<T>) {
    verifyListSize(BaseFileReader.EXPECTED_NUM_QUAKES_WHEN_NO_DATA, items)
}