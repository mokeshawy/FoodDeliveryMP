package org.saham.fooddelivery.features.order_details_screen.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.saham.fooddelivery.features.order_details_screen.data.repository.OrderDetailsRepositoryImpl
import org.saham.fooddelivery.features.order_details_screen.domain.repository.OrderDetailsRepository
import org.saham.fooddelivery.features.order_details_screen.domain.usecase.OrderDetailsUseCase
import org.saham.fooddelivery.features.order_details_screen.presentation.viewmodel.OrderDetailsViewModel


val orderDetailsRepositoryModule = module {
    single<OrderDetailsRepository> { OrderDetailsRepositoryImpl(foodDevilryServices = get()) }
}

val ordersDetailsUseCaseModule = module {
    single { OrderDetailsUseCase(orderDetailsRepository = get(), orderDao = get()) }
}

val orderDetailsViewModelModule = module {

    viewModel { (savedStateHandle: SavedStateHandle) ->
        OrderDetailsViewModel(
            savedStateHandle = savedStateHandle,
            orderDetailsUseCase = get(),
            webSocketHelper = get()
        )
    }

}