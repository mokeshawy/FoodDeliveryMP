package org.saham.fooddelivery.features.order_list_screen.presentation.viewmodel

import com.shared.core.bases.base_viewmodel.BaseViewModel
import com.shared.core.extensions.collectOnFlowState
import com.shared.core.extensions.viewModelScope
import org.saham.fooddelivery.features.order_list_screen.domain.event.OrderListIntent
import org.saham.fooddelivery.features.order_list_screen.domain.model.state.OrdersListUiState
import org.saham.fooddelivery.features.order_list_screen.domain.usecase.OrdersListUseCase


class OrderListViewModel(
    private val ordersListUseCase: OrdersListUseCase
) : BaseViewModel<OrderListIntent, OrdersListUiState>(OrdersListUiState()) {


    init {
        sendGetOrdersListIntent()
    }


    fun refresh() {
        resetOrdersListUiState()
        sendGetOrdersListIntent()
    }

    fun sendGetOrdersListIntent() = sendIntent(OrderListIntent.GetOrdersListIntent)


    override fun processIntent(intent: OrderListIntent) {
        when (intent) {
            OrderListIntent.GetOrdersListIntent -> reduceOrdersListResponseState()
        }
    }


    private fun reduceOrdersListResponseState() = viewModelScope {
        updateStateFlow { copy(isLoading = true) }
        ordersListUseCase().collectOnFlowState(
            onError = {
                handleError(it) { updateStateFlow { copy(isLoading = false, error = it) } }
            },
            onSuccess = {
                val ordersListUiModel = ordersListUseCase.ordersListUiModel
                updateStateFlow { copy(isLoading = false, orderUiModel = ordersListUiModel) }
            })
    }


    private fun resetOrdersListUiState() =
        updateStateFlow { copy(isLoading = false, error = null, orderUiModel = null) }
}