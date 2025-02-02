package com.android.energiterbarukan.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.energiterbarukan.database.User
import com.android.energiterbarukan.repository.UserRepository

class LoginViewModel(application: Application): ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)

    fun getAllUser(): LiveData<List<User>> = mUserRepository.getAllUser()
}