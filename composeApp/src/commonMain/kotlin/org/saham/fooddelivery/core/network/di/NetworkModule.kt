package org.saham.fooddelivery.core.network.di

import org.koin.dsl.module
import org.saham.fooddelivery.core.network.NetworkModule
import org.saham.fooddelivery.features.food_delivery_services.FoodDeliveryServices

val networkModule = module {
    single { NetworkModule() }
    single { FoodDeliveryServices(get()) }
}