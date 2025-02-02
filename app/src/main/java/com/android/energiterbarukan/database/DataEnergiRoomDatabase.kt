package com.android.energiterbarukan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataEnergi::class], version = 1)
abstract class DataEnergiRoomDatabase : RoomDatabase() {
    abstract fun dataEnergiDao(): DataEnergiDao

    companion object {
        @Volatile
        private var INSTANCE: DataEnergiRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): DataEnergiRoomDatabase {
            if (INSTANCE == null) {
                synchronized(DataEnergiRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataEnergiRoomDatabase::class.java, "data_energi_database"
                    )
                        .build()
                }
            }
            return INSTANCE as DataEnergiRoomDatabase
        }
    }
}