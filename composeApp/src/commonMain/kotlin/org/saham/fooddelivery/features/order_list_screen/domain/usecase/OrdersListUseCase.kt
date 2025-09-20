package org.saham.fooddelivery.features.order_list_screen.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import org.saham.fooddelivery.core.state.State
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto
import org.saham.fooddelivery.features.common.domain.mapper.toOrderUiModel
import org.saham.fooddelivery.features.order_list_screen.domain.repository.OrdersListRepository

class OrdersListUseCase(private val ordersListRepository: OrdersListRepository) {


    private var ordersList: List<OrderResponseDto>? = null

    operator fun invoke() = channelFlow {
        val response = async { ordersListRepository.getOrdersList() }
        response.await().collect {
            if (it is State.Success) {
                ordersList = it.data
            }
            send(it)
        }
    }

    val ordersListUiModel get() = ordersList?.map { it.toOrderUiModel() } ?: emptyList()
}