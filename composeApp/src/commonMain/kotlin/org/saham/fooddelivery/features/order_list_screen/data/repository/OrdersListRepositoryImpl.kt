package org.saham.fooddelivery.features.order_list_screen.data.repository


import com.shared.core.bases.base_repository.BaseRepository
import com.shared.core.state.State
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto
import org.saham.fooddelivery.features.food_delivery_services.FoodDeliveryServices
import org.saham.fooddelivery.features.order_list_screen.domain.repository.OrdersListRepository

class OrdersListRepositoryImpl(private val foodDevilryServices: FoodDeliveryServices) :
    BaseRepository<Any, List<OrderResponseDto>>(),
    OrdersListRepository {

    override suspend fun getOrdersList() = flow {
        emit(getOperationState(requestDto = Any()))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: Any): State<List<OrderResponseDto>> {
        val response = foodDevilryServices.getOrdersList()
        return response.handleOrdersListResponseState()
    }


    private suspend fun HttpResponse.handleOrdersListResponseState(): State<List<OrderResponseDto>> {
        val isSuccessful = status.value == 200
        return when {
            isSuccessful -> State.Success(body())
            else -> getNotSuccessfulResponseState(response = this)
        }
    }
}