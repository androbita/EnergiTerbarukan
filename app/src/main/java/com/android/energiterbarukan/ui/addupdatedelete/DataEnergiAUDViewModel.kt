package com.android.energiterbarukan.ui.addupdatedelete

import android.app.Application
import androidx.lifecycle.ViewModel
import com.android.energiterbarukan.database.DataEnergi
import com.android.energiterbarukan.repository.DataEnergiRepository

class DataEnergiAUDViewModel(application: Application) : ViewModel() {
    private val dataEnergiRepository = DataEnergiRepository(application)

    fun insert(dataEnergi: DataEnergi) {
        dataEnergiRepository.insert(dataEnergi)
    }

    fun delete(dataEnergi: DataEnergi) {
        dataEnergiRepository.delete(dataEnergi)
    }

    fun update(dataEnergi: DataEnergi) {
        dataEnergiRepository.update(dataEnergi)
    }
}