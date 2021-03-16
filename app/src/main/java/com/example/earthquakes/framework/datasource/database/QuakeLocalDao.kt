package com.example.earthquakes.framework.datasource.database

import androidx.room.*

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
