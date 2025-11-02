package org.saham.fooddelivery.features.order_details_screen.domain.usecase

import org.saham.fooddelivery.core.local_database.OrderDao
import org.saham.fooddelivery.features.order_details_screen.domain.repository.OrderDetailsRepository

class OrderDetailsUseCase(
    private val orderDetailsRepository: OrderDetailsRepository,
    private val orderDao: OrderDao
) {


    suspend operator fun invoke(orderId: Int) =
        orderDetailsRepository.getOrderDetailsById(orderId = orderId)


    fun getOrderById(orderId: Int) = orderDao.getOrderByIdAsFlow(orderId = orderId)
}