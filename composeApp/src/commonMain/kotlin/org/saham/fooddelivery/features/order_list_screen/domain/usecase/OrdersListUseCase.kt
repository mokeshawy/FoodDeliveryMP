package org.saham.fooddelivery.features.order_list_screen.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.stateIn
import org.saham.fooddelivery.core.local_database.OrderDao
import org.saham.fooddelivery.features.order_list_screen.domain.repository.OrdersListRepository

class OrdersListUseCase(
    private val ordersListRepository: OrdersListRepository,
    private val orderDao: OrderDao
) {


    suspend operator fun invoke() = ordersListRepository.getOrdersList()

    suspend fun getAllAsFlow(coroutineScope: CoroutineScope) =
        orderDao.getAllAsFlow().stateIn(coroutineScope).value
}