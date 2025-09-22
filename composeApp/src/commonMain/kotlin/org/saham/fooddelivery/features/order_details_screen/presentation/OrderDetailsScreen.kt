package org.saham.fooddelivery.features.order_details_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shared.core.ui.failure_view.FailureView
import com.shared.core.ui.main_top_bar.MainTopBar
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.ic_vector_arrow_back
import fooddeliverymp.composeapp.generated.resources.ic_vector_error
import fooddeliverymp.composeapp.generated.resources.orderDetails
import fooddeliverymp.composeapp.generated.resources.tapToLoadContent
import org.koin.compose.viewmodel.koinViewModel
import org.saham.fooddelivery.features.common_composaple.OrderItem
import org.saham.fooddelivery.features.order_details_screen.presentation.viewmodel.OrderDetailsViewModel

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
        title = Res.string.orderDetails,
        leftIcon = Res.drawable.ic_vector_arrow_back,
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
                    FailureView(
                        tapText = Res.string.tapToLoadContent,
                        icon = Res.drawable.ic_vector_error,
                        onTapToRefresh = viewModel::refresh
                    )
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