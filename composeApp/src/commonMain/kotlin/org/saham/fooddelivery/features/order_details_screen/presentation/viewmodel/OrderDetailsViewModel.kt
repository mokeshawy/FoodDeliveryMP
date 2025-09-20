package org.saham.fooddelivery.features.order_details_screen.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import org.saham.fooddelivery.core.app_logger.AppLogger
import org.saham.fooddelivery.core.bases.base_viewmodel.BaseViewModel
import org.saham.fooddelivery.core.error.ErrorLogPriority
import org.saham.fooddelivery.core.extensions.collectOnFlowState
import org.saham.fooddelivery.core.extensions.viewModelScope
import org.saham.fooddelivery.core.websocket.WebSocketHelper
import org.saham.fooddelivery.features.order_details_screen.domain.event.OrderDetailsIntent
import org.saham.fooddelivery.features.order_details_screen.domain.model.state.OrdersDetailsUiState
import org.saham.fooddelivery.features.order_details_screen.domain.usecase.OrderDetailsUseCase
import org.saham.fooddelivery.features.order_details_screen.graph.OrderDetailsScreen


class OrderDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val orderDetailsUseCase: OrderDetailsUseCase,
    private val webSocketHelper: WebSocketHelper
) : BaseViewModel<OrderDetailsIntent, OrdersDetailsUiState>(OrdersDetailsUiState()) {

    val orderId = savedStateHandle.toRoute<OrderDetailsScreen>().orderId

    init {
        sendGetOrderDetailsByIdIntent()
        initWebsocketConnection()
        onMessageReceived()
    }

    fun refresh() {
        resetOrderDetailsUiState()
        sendGetOrderDetailsByIdIntent()
    }

    private fun sendGetOrderDetailsByIdIntent() =
        sendIntent(OrderDetailsIntent.GetOrderDetailsByIdIntent(orderId = orderId))

    override fun processIntent(intent: OrderDetailsIntent) {
        when (intent) {
            is OrderDetailsIntent.GetOrderDetailsByIdIntent -> {
                reduceOrderDetailsResponseState(orderId = intent.orderId)
            }
        }
    }

    private fun reduceOrderDetailsResponseState(orderId: Int) = viewModelScope {
        updateStateOf { copy(isLoading = true) }
        orderDetailsUseCase(orderId = orderId).collectOnFlowState(
            onError = {
                handleError(it) { updateStateOf { copy(isLoading = false, error = it) } }
            }, onSuccess = {
                val orderUiModel = orderDetailsUseCase.orderUiModel
                updateStateOf { copy(isLoading = false, orderUiModel = orderUiModel) }
            }
        )
    }

    private fun resetOrderDetailsUiState() =
        updateStateOf { copy(isLoading = false, error = null, orderUiModel = null) }


    private fun initWebsocketConnection() = webSocketHelper.connect()

    fun sendMessage(message: String) = webSocketHelper.sendMessage(message)


    private fun onMessageReceived() {
        webSocketHelper.onMessageReceived = { message ->
            AppLogger.i(
                priority = ErrorLogPriority.INFO.level,
                tag = "WebSocket",
                "onMessageReceived: $message"
            )
        }
    }


    override fun onCleared() {
        super.onCleared()
        webSocketHelper.close()
    }
}