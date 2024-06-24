package com.id.masel.healtime.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.id.masel.healtime.Injection
import com.id.masel.healtime.data.Repository
import com.id.masel.healtime.view.MainViewModel
import com.id.masel.healtime.view.add.AddStoryViewModel
import com.id.masel.healtime.view.login.LoginViewModel
import com.id.masel.healtime.view.register.RegisterViewModel
import com.id.masel.healtime.view.splashscreen.SplashscreenViewModel

class ViewModelFactory constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @OptIn(ExperimentalPagingApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SplashscreenViewModel::class.java) -> {
                SplashscreenViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}