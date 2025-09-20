package org.saham.fooddelivery.features.order_details_screen.data.repository

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.saham.fooddelivery.core.bases.base_repository.BaseRepository
import org.saham.fooddelivery.core.state.State
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto
import org.saham.fooddelivery.features.food_delivery_services.FoodDeliveryServices
import org.saham.fooddelivery.features.order_details_screen.domain.repository.OrderDetailsRepository


class OrderDetailsRepositoryImpl(
    private val foodDevilryServices: FoodDeliveryServices
) : BaseRepository<Int, Map<String, OrderResponseDto>>(), OrderDetailsRepository {

    override suspend fun getOrderDetailsById(orderId: Int) = flow {
        emit(getOperationState(requestDto = orderId))
    }.flowOn(context = dispatcher)

    override suspend fun performApiCall(requestDto: Int): State<Map<String, OrderResponseDto>> {
        val response = foodDevilryServices.getOrderDetailsById(orderId = requestDto)
        return response.handleOrderDetailsResponseState()
    }

    private suspend fun HttpResponse.handleOrderDetailsResponseState(): State<Map<String, OrderResponseDto>> {
        val isSuccessful = status.value == 200
        return when {
            isSuccessful -> State.Success(data = body())
            else -> getNotSuccessfulResponseState(response = this)
        }
    }
}