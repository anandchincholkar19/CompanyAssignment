package com.example.myapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.network.repository.ApiHelper
import com.example.myapplication.network.repository.OrderRepository
import com.example.myapplication.view.orderlist.OrderListViewModel

class OrderViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(OrderRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
