package org.saham.fooddelivery.core.di

import org.koin.dsl.module
import com.shared.core.network.NetworkModule
import org.saham.fooddelivery.features.food_delivery_services.FoodDeliveryServices

val networkModule = module {
    single { NetworkModule() }
    single { FoodDeliveryServices(get()) }
}