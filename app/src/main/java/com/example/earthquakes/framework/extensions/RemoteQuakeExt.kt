package com.example.earthquakes.framework.extensions

import com.example.earthquakes.framework.datasource.network.model.RemoteQuake
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.datasource.network.model.QuakeFeed

fun QuakeFeed?.toItems(): List<Quake> {
    val items = mutableListOf<Quake>()
    if (this == null || this.earthquakes == null) return items.toList()
    val filteredList = this.earthquakes!!.filter { it != null && !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it!!.toItem(it.id!!)
    })
    return items.toList()
}

private fun RemoteQuake.toItem(id: String): Quake {
    return Quake(
        id,
        this.datetime,
        this.depth,
        this.longitude,
        this.latitude,
        this.source,
        this.magnitude
    )
}
