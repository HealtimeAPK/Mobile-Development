package com.id.masel.healtime.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import com.id.masel.healtime.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object ApiConfig {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private fun client(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor{
            val origin = it.request()
            val requestBuilder = origin.newBuilder()
            val request = requestBuilder.build()
            it.proceed(request)
        }. build()
    fun getApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
}