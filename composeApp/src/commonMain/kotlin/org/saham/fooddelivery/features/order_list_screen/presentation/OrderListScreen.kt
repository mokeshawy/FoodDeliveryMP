package org.saham.fooddelivery.features.order_list_screen.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.saham.fooddelivery.features.order_list_screen.presentation.viewmodel.OrderListViewModel


@Composable
fun OrdersListScreen(
    viewModel: OrderListViewModel = koinViewModel(),
    onNavigateToOrderDetails: (orderId: Int) -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value

}