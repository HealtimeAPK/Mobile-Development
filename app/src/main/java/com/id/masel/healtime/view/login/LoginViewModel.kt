package com.id.masel.healtime.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.masel.healtime.data.Repository
import com.id.masel.healtime.data.Resource
import com.id.masel.healtime.data.model.LoginResponse

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun userLogin(
        email: String,
        password: String
    ): LiveData<Resource<LoginResponse>> =
        repository.userLogin(email, password)
}