package com.example.myapplication.view.liseteners

import com.example.myapplication.model.Customer

interface OnOrderItemClickListener {
    fun onOrderItemClick(customer: Customer, position: Int, orderStatus: String, imageUrl: String)
}