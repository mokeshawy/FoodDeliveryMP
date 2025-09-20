package org.saham.fooddelivery.features.order_list_screen.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.saham.fooddelivery.features.common_composaple.OrderItem
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.app_name
import org.koin.compose.viewmodel.koinViewModel
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel
import org.saham.fooddelivery.features.order_list_screen.presentation.viewmodel.OrderListViewModel
import org.saham.fooddelivery.ui_component.failure_view.FailureView
import org.saham.fooddelivery.ui_component.main_top_bar.MainTopBar
import org.saham.fooddelivery.ui_component.ui_generic.GeneralLazyColumn


@Composable
fun OrdersListScreen(
    viewModel: OrderListViewModel = koinViewModel(),
    onNavigateToOrderDetails: (orderId: Int) -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value
    MainTopBar(
        isRefreshing = uiState.isLoading,
        title = Res.string.app_name,
        onRefresh = viewModel::refresh,
        isShowBottomBar = true
    ) {
        when {
            uiState.error != null -> {
                FailureView(onTapToRefresh = viewModel::refresh)
            }

            uiState.orderUiModel?.isNotEmpty() == true -> {
                OrderListContent(
                    orderListUiModelList = uiState.orderUiModel,
                    onItemClicked = { id -> id?.let { onNavigateToOrderDetails(it) } }
                )
            }
        }
    }
}

@Composable
fun OrderListContent(
    orderListUiModelList: List<OrderUiModel?>,
    onItemClicked: (Int?) -> Unit
) {
    GeneralLazyColumn(
        modifier = Modifier.padding(8.dp),
        list = orderListUiModelList
    ) {
        OrderItem(
            customerName = it?.customerName ?: "-",
            restaurant = it?.restaurant ?: "-",
            orderStatus = it?.status ?: "-",
            onClicked = { onItemClicked(it?.id) }
        )
    }
}