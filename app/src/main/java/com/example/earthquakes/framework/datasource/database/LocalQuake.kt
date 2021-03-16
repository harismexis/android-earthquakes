package com.example.earthquakes.framework.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quake_table")
data class LocalQuake(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "datetime") val datetime: String?,
    @ColumnInfo(name = "depth") val depth: Float?,
    @ColumnInfo(name = "longitude") val longitude: Float?,
    @ColumnInfo(name = "latitude") val latitude: Float?,
    @ColumnInfo(name = "source") val source: String?,
    @ColumnInfo(name = "magnitude") val magnitude: Float?
)
