package org.saham.fooddelivery.features.order_details.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.saham.fooddelivery.features.order_details.data.repository.OrderDetailsRepositoryImpl
import org.saham.fooddelivery.features.order_details.domain.repository.OrderDetailsRepository
import org.saham.fooddelivery.features.order_details.domain.usecase.OrderDetailsUseCase
import org.saham.fooddelivery.features.order_details.presentation.viewmodel.OrderDetailsViewModel


val orderDetailsRepositoryModule = module {
    single<OrderDetailsRepository> { OrderDetailsRepositoryImpl(foodDevilryServices = get()) }
}

val ordersDetailsUseCaseModule = module {
    single { OrderDetailsUseCase(orderDetailsRepository = get()) }
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