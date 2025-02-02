package com.android.energiterbarukan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataEnergiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dataEnergi: DataEnergi)

    @Update
    fun update(dataEnergi: DataEnergi)

    @Delete
    fun delete(dataEnergi: DataEnergi)

    @Query("SELECT * from dataEnergi ORDER BY id ASC")
    fun getAllDataEnergi(): LiveData<List<DataEnergi>>
}