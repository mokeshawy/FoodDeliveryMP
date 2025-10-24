package org.saham.fooddelivery.features.order_list_screen.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.shared.core.bases.base_viewmodel.BaseViewModel
import com.shared.core.extensions.viewModelScope
import com.shared.core.state.State
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
        ordersListUseCase().collect { result ->
            val ordersListUiModel = ordersListUseCase.getAllAsFlow(coroutineScope = viewModelScope)
            when{
                ordersListUiModel.isEmpty() && result is State.Error -> handleError(result.error) {
                    updateStateFlow { copy(isLoading = false, error = result.error) }
                }
                else -> updateStateFlow { copy(isLoading = false, orderUiModel = ordersListUiModel) }
            }
        }
    }


    private fun resetOrdersListUiState() =
        updateStateFlow { copy(isLoading = false, error = null, orderUiModel = null) }
}