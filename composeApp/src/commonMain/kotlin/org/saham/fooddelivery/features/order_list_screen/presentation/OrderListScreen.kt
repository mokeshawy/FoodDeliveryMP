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
import com.shared.core.ui.failure_view.FailureView
import com.shared.core.ui.main_top_bar.MainTopBar
import com.shared.core.ui.ui_generic.GeneralLazyColumn
import fooddeliverymp.composeapp.generated.resources.ic_vector_error
import fooddeliverymp.composeapp.generated.resources.tapToLoadContent


@Composable
fun OrdersListScreen(
    viewModel: OrderListViewModel = koinViewModel(),
    onNavigateToOrderDetails: (orderId: Int) -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value
    MainTopBar(
        isRefreshing = uiState.isLoading,
        title = Res.string.app_name,
        onRefresh = viewModel::refresh
    ) {
        when {
            uiState.error != null -> {
                FailureView(
                    tapText = Res.string.tapToLoadContent,
                    icon = Res.drawable.ic_vector_error,
                    onTapToRefresh = viewModel::refresh
                )
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