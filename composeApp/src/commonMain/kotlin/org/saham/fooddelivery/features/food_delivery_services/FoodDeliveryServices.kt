package org.saham.fooddelivery.features.food_delivery_services

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import com.shared.core.network.NetworkModule

class FoodDeliveryServices(private val networkModule: NetworkModule) {

    suspend fun getOrdersList() = networkModule.api.get {
        url("orders.json")
    }


    suspend fun getOrderDetailsById(orderId: Int) = networkModule.api.get {
        url("orders.json?orderBy=\"id\"")
        parameter("equalTo", orderId)
    }

}