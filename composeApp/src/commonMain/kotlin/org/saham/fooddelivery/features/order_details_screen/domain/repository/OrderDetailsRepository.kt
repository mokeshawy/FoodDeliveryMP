package org.saham.fooddelivery.features.order_details_screen.domain.repository

import com.shared.core.state.State
import kotlinx.coroutines.flow.Flow
import org.saham.fooddelivery.features.common.data.response.OrderResponseDto

interface OrderDetailsRepository {


    suspend fun getOrderDetailsById(orderId: Int): Flow<State<Map<String, OrderResponseDto>>>
}