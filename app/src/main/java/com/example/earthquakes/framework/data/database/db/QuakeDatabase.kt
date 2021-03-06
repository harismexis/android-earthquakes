package com.example.earthquakes.framework.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.earthquakes.framework.data.database.dao.QuakeLocalDao
import com.example.earthquakes.framework.data.database.table.LocalQuake

@Database(entities = [LocalQuake::class], version = 1, exportSchema = false)
abstract class QuakeDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: QuakeDatabase? = null
        private const val DATABASE_FILE_NAME = "quake_room_database"

        fun getDatabase(
            context: Context
        ): QuakeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuakeDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): QuakeLocalDao

}
