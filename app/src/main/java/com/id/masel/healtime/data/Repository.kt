package com.id.masel.healtime.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.id.masel.healtime.UserPreference
import com.id.masel.healtime.data.local.DiseaseDatabase
import com.id.masel.healtime.data.local.DiseaseEntity
import com.id.masel.healtime.data.model.LoginResponse
import com.id.masel.healtime.data.model.RegisterResponse
import com.id.masel.healtime.data.model.DiseaseResponse
import com.id.masel.healtime.data.model.UploadResponse
import com.id.masel.healtime.data.remote.ApiConfig
import com.id.masel.healtime.data.remote.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Repository (private val preference: UserPreference, private val database: DiseaseDatabase) {
    private val retrofit: ApiService = ApiConfig.getApiService()

    fun userLogin(email: String, password: String): LiveData<Resource<LoginResponse>> = liveData{
        try {
            emit(Resource.Loading())
            val response = retrofit.loginUser(email, password)
            val token = response.loginResult.token
            preference.saveUser(token)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    fun userRegister(name: String, email: String, password: String): LiveData<Resource<RegisterResponse>> = liveData {
        try {
            emit(Resource.Loading())
            val response = retrofit.registerUser(name = name, email = email, password = password)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    fun getDisease(token: String): LiveData<PagingData<DiseaseEntity>> {
        val tokenId = getToken(token)
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = DiseaseRemoteMediator(
                database,
                retrofit,
                tokenId
            ),


        ).liveData
    }



    fun getUser(): LiveData<String?> = preference.getUser().asLiveData()

    suspend fun logout() = preference.logout()

    private fun getToken(token: String): String = "Bearer $token"
}