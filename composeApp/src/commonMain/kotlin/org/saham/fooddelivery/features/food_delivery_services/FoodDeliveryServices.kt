package org.saham.fooddelivery.features.food_delivery_services

import io.ktor.client.request.get
import io.ktor.client.request.url
import org.saham.fooddelivery.core.network.NetworkModule

class FoodDeliveryServices (private val networkModule: NetworkModule) {

    suspend fun getOrdersList() = networkModule.api.get {
        url("orders.json")
    }

}