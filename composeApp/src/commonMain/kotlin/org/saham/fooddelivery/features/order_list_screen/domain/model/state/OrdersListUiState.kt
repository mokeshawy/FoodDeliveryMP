package org.saham.fooddelivery.features.order_list_screen.domain.model.state

import org.saham.fooddelivery.core.bases.base_viewmodel.ViewState
import org.saham.fooddelivery.core.error.AppError
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel

data class OrdersListUiState(
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val orderUiModel: List<OrderUiModel>? = null
) : ViewState
