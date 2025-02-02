package com.android.energiterbarukan.ui.register

import android.app.Application
import androidx.lifecycle.ViewModel
import com.android.energiterbarukan.database.User
import com.android.energiterbarukan.repository.UserRepository

class UserRegisterViewModel(application: Application): ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)

    fun insert(user: User){
        mUserRepository.insert(user)
    }
}