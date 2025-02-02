package com.android.energiterbarukan.ui.homepage

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.energiterbarukan.database.DataEnergi
import com.android.energiterbarukan.repository.DataEnergiRepository

class MainViewModel(application: Application) : ViewModel() {
    private val dataEnergiRepository = DataEnergiRepository(application)

    fun getAllDataEnergi(): LiveData<List<DataEnergi>> = dataEnergiRepository.getAllDataEnergi()
}