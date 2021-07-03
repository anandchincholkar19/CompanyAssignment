package com.example.myapplication.network.repository

import com.example.myapplication.network.api.OrderApiService

class ApiHelper(private val apiService: OrderApiService) {
    suspend fun getOrders() = apiService.getOrders()
}
