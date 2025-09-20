package org.saham.fooddelivery.features.order_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saham.fooddelivery.features.common_composaple.OrderItem
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.ic_vector_arrow_back
import fooddeliverymp.composeapp.generated.resources.orderDetails
import org.koin.compose.viewmodel.koinViewModel
import org.saham.fooddelivery.features.order_details.presentation.viewmodel.OrderDetailsViewModel
import org.saham.fooddelivery.ui_component.failure_view.FailureView
import org.saham.fooddelivery.ui_component.main_top_bar.MainTopBar

@Composable
fun OrderDetailsScreen(
    viewModel: OrderDetailsViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiState
    val orderUiModel = uiState.orderUiModel

    MainTopBar(
        isRefreshing = uiState.isLoading,
        onRefresh = viewModel::refresh,
        leftIcon = Res.drawable.ic_vector_arrow_back,
        title = Res.string.orderDetails,
        onLeftIconClicked = onBackClicked
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {

            when {
                uiState.error != null -> {
                    FailureView(onTapToRefresh = viewModel::refresh)
                }

                uiState.orderUiModel != null -> {
                    OrderItem(
                        customerName = orderUiModel.customerName,
                        restaurant = orderUiModel.restaurant,
                        orderStatus = orderUiModel.status,
                    )

                    viewModel.sendMessage(message = "id: ${orderUiModel.id} status: ${orderUiModel.status}")
                }
            }
        }
    }
}