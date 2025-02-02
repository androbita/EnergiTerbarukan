package com.android.energiterbarukan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.energiterbarukan.database.DataEnergi
import com.android.energiterbarukan.database.DataEnergiDao
import com.android.energiterbarukan.database.DataEnergiRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DataEnergiRepository(application: Application) {
    private val dataEnergiDao: DataEnergiDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DataEnergiRoomDatabase.getDatabase(application)
        dataEnergiDao = db.dataEnergiDao()
    }

    fun getAllDataEnergi(): LiveData<List<DataEnergi>> = dataEnergiDao.getAllDataEnergi()

    fun insert(dataEnergi: DataEnergi) {
        executorService.execute { dataEnergiDao.insert(dataEnergi) }
    }

    fun delete(dataEnergi: DataEnergi) {
        executorService.execute { dataEnergiDao.delete(dataEnergi) }
    }

    fun update(dataEnergi: DataEnergi) {
        executorService.execute { dataEnergiDao.update(dataEnergi) }
    }
}