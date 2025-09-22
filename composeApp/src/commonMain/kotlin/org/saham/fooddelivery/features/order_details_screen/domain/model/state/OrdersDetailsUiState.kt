package org.saham.fooddelivery.features.order_details_screen.domain.model.state

import com.shared.core.bases.base_viewmodel.ViewState
import com.shared.core.error.AppError
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel

data class OrdersDetailsUiState(
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val orderUiModel: OrderUiModel? = null
) : ViewState
