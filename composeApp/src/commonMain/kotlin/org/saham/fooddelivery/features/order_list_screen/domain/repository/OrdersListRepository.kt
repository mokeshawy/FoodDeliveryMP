package org.saham.fooddelivery.features.order_list_screen.domain.repository


import com.shared.core.state.State
import kotlinx.coroutines.flow.Flow
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto

interface OrdersListRepository {

    suspend fun getOrdersList(): Flow<State<List<OrderResponseDto>>>
}