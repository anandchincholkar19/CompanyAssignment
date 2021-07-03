package com.example.myapplication.model

import java.io.Serializable

data class Order(
    val customers: List<Customers>,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)

data class Customers(
    val customer:  Customer,
    val imageUrl: String,
    val jobIndicator: String,
    val lastUpdateDate: String,
    val orderRef: String,
    val scheduleDate: String,
    val scheduleEndTime: String,
    val scheduleStartTime: String,
    val serviceRequested: String,
    val serviceSpecialInstructions: String,
    var status: String
)

data class Customer(
    val address: String,
    val city: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    var state: String,
    val zip: String,
    val zipSuffix: String,
    var isOrderFinished: Boolean
): Serializable
