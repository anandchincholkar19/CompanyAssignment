package com.example.myapplication.network.repository

import com.example.myapplication.network.api.OrderApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryBuilder {
    private const val BASE_URL = "https://run.mocky.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: OrderApiService = getRetrofit().create(OrderApiService::class.java)
}
