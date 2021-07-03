package com.example.myapplication.network.repository

class OrderRepository constructor(private val apiHelper: ApiHelper) {
    suspend fun getOrders() = apiHelper.getOrders()
}