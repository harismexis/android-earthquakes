package com.example.earthquakes.framework.extensions

import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.data.database.table.LocalQuake

fun List<LocalQuake?>?.toItems(): List<Quake> {
    val items = mutableListOf<Quake>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalQuake.toItem(): Quake {
    return Quake(
        this.id,
        this.datetime,
        this.depth,
        this.longitude,
        this.latitude,
        this.source,
        this.magnitude
    )
}

fun List<Quake?>?.toLocalItems(): List<LocalQuake> {
    val localItems = mutableListOf<LocalQuake>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Quake.toLocalItem(): LocalQuake {
    return LocalQuake(
        this.id,
        this.datetime,
        this.depth,
        this.longitude,
        this.latitude,
        this.source,
        this.magnitude
    )
}
