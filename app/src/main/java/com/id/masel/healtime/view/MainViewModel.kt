package com.id.masel.healtime.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.id.masel.healtime.data.Repository
import com.id.masel.healtime.data.local.DiseaseEntity

class MainViewModel(private val repository: Repository): ViewModel() {
    suspend fun logout() = repository.logout()

    fun getSymptom(token: String): LiveData<PagingData<DiseaseEntity>> =
        repository.getSymptom(token).cachedIn(viewModelScope)
}