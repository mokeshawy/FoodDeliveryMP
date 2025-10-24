package org.saham.fooddelivery.features.order_list_screen.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.saham.fooddelivery.features.order_list_screen.data.repository.OrdersListRepositoryImpl
import org.saham.fooddelivery.features.order_list_screen.domain.repository.OrdersListRepository
import org.saham.fooddelivery.features.order_list_screen.domain.usecase.OrdersListUseCase
import org.saham.fooddelivery.features.order_list_screen.presentation.viewmodel.OrderListViewModel


val orderListRepositoryModule = module {
    single<OrdersListRepository> { OrdersListRepositoryImpl(foodDevilryServices = get(), orderDao = get()) }
}

val ordersListUseCaseModule = module {
    single { OrdersListUseCase(ordersListRepository = get(), orderDao = get()) }
}

val orderListViewModelModule = module {
    viewModel { OrderListViewModel(ordersListUseCase = get()) }
}