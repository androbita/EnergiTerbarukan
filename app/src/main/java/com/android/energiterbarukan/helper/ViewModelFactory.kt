package com.android.energiterbarukan.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.energiterbarukan.ui.addupdatedelete.DataEnergiAUDViewModel
import com.android.energiterbarukan.ui.homepage.MainViewModel
import com.android.energiterbarukan.ui.login.LoginViewModel
import com.android.energiterbarukan.ui.register.UserRegisterViewModel

class ViewModelFactory private constructor(private val mApplication: Application):
    ViewModelProvider.NewInstanceFactory(){

        companion object{
            @Volatile
            private var INSTANCE: ViewModelFactory? = null

            @JvmStatic
            fun getInstance(application: Application): ViewModelFactory{
                if (INSTANCE == null){
                    synchronized(ViewModelFactory::class.java){
                        INSTANCE = ViewModelFactory(application)
                    }
                }
                return INSTANCE as ViewModelFactory
            }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(UserRegisterViewModel::class.java)){
            return UserRegisterViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(DataEnergiAUDViewModel::class.java)){
            return DataEnergiAUDViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}