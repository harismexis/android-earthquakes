package com.example.earthquakes.util

import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.network.model.QuakesResponse
import com.example.earthquakes.framework.data.network.model.RemoteQuake
import org.junit.Assert

class QuakeRemoteVerificator {

    fun verifyItemsAgainstQuakesResponse(
        actual: List<Quake>,
        resp: QuakesResponse?
    ) {
        if (resp == null || resp.earthquakes.isNullOrEmpty()) {
            verifyListSizeForNoData(actual)
            return
        }
        val remoteItems = resp.earthquakes!!
        remoteItems.forEach lit@{ remoteItem ->
            if (remoteItem == null || remoteItem.id.isNullOrBlank()) return@lit
            actual.forEach { actualItem ->
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

