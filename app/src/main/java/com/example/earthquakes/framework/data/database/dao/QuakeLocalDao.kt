package com.example.earthquakes.framework.data.database.dao

import androidx.room.*
import com.example.earthquakes.framework.data.database.table.LocalQuake

@Dao
interface QuakeLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalQuake>)

    @Query("SELECT * FROM quake_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): LocalQuake?

    @Query("SELECT * FROM quake_table")
    suspend fun getAllItems(): List<LocalQuake?>?

    @Query("DELETE FROM quake_table")
    suspend fun deleteAll()

}
