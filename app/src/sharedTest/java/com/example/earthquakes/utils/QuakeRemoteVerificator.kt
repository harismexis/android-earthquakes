package com.example.earthquakes.utils

import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.network.model.QuakeFeed
import com.example.earthquakes.framework.data.network.model.RemoteQuake
import org.junit.Assert

class QuakeRemoteVerificator {

    fun verifyItemsAgainstRemoteFeed(
        actualItems: List<Quake>,
        remoteFeed: QuakeFeed?
    ) {
        if (remoteFeed == null || remoteFeed.earthquakes.isNullOrEmpty()) {
            verifyListSizeForNoData(actualItems)
            return
        }
        val remoteItems = remoteFeed.earthquakes!!
        remoteItems.forEach lit@{ remoteItem ->
            if (remoteItem == null || remoteItem.id.isNullOrBlank()) return@lit
            actualItems.forEach { actualItem ->
                if (remoteItem.id == actualItem.id) {
                    verifyItemAgainstRemoteItem(actualItem, remoteItem)
                    return@lit
                }
            }
        }
    }

    private fun verifyItemAgainstRemoteItem(
        actual: Quake,
        expected: RemoteQuake
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

