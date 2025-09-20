package org.saham.fooddelivery.features.order_details_screen.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import org.saham.fooddelivery.core.state.State
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto
import org.saham.fooddelivery.features.common.domain.mapper.toOrderUiModel
import org.saham.fooddelivery.features.order_details_screen.domain.repository.OrderDetailsRepository

class OrderDetailsUseCase(private val orderDetailsRepository: OrderDetailsRepository) {


    private var orderResponseDto: OrderResponseDto? = null


    operator fun invoke(orderId: Int) = channelFlow {
        val response = async { orderDetailsRepository.getOrderDetailsById(orderId = orderId) }
        response.await().collect {
            if (it is State.Success) {
                it.data?.values?.map { value -> orderResponseDto = value }
            }
            send(it)
        }
    }


    val orderUiModel get() = orderResponseDto?.toOrderUiModel()
}