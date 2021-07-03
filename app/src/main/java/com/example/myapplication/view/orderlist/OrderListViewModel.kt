package com.example.myapplication.view.orderlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.network.repository.OrderRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.Dispatchers

class OrderListViewModel(private val orderRepository: OrderRepository) :ViewModel(){

    fun getOrders() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderRepository.getOrders()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
