package com.example.earthquakes.framework.data.database

import com.example.earthquakes.data.QuakeBaseLocalDataSource
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.extensions.toItem
import com.example.earthquakes.framework.extensions.toItems
import com.example.earthquakes.framework.extensions.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuakeLocalDataSource @Inject constructor(
    private val dao: QuakeLocalDao
) : QuakeBaseLocalDataSource {

    override suspend fun storeQuakes(items: List<Quake>) {
        dao.deleteAll()
        dao.insertItems(items.toLocalItems())
    }

    override suspend fun getQuake(itemId: String): Quake? {
        val localItem = dao.getItemById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getQuakes(): List<Quake> {
        return dao.getAllItems().toItems()
    }

}