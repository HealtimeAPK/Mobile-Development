package com.id.masel.healtime.view.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.masel.healtime.data.Repository

class SplashscreenViewModel(private val repository: Repository) : ViewModel() {
    fun getUser(): LiveData<String?> = repository.getUser()
}