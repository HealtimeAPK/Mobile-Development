package com.id.masel.healtime.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.masel.healtime.data.Repository
import com.id.masel.healtime.data.Resource
import com.id.masel.healtime.data.model.LoginResponse
import com.id.masel.healtime.data.model.RegisterResponse

class RegisterViewModel(private val repository: Repository) : ViewModel(){
    fun userRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<Resource<RegisterResponse>> =
        repository.userRegister(name, email, password)
    fun userLogin(
        email: String,
        password: String
    ): LiveData<Resource<LoginResponse>> =
        repository.userLogin(email, password)
}