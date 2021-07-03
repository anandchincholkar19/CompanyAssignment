package com.example.myapplication.network.api

import com.example.myapplication.model.Order
import retrofit2.http.GET

interface OrderApiService {
    @GET("v3/eecc4707-ea59-4be3-b00d-eda5a2240493/")
    suspend fun getOrders(): Order
}