package org.saham.fooddelivery.features.order_details_screen.domain.repository

import kotlinx.coroutines.flow.Flow
import org.saham.fooddelivery.core.state.State
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto

interface OrderDetailsRepository {


    suspend fun getOrderDetailsById(orderId: Int): Flow<State<Map<String, OrderResponseDto>>>
}