package org.saham.fooddelivery.features.order_details_screen.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.shared.core.app_logger.AppLogger
import com.shared.core.bases.base_viewmodel.BaseViewModel
import com.shared.core.error.ErrorLogPriority
import com.shared.core.extensions.viewModelScope
import com.shared.core.state.State
import com.shared.core.websocket.WebSocketHelper
import kotlinx.coroutines.flow.stateIn
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
        val orderById =
            orderDetailsUseCase.getOrderById(orderId = orderId)?.stateIn(viewModelScope)?.value
        updateStateOf { copy(isLoading = true) }
        orderDetailsUseCase(orderId = orderId).collect { result ->
            when {
                orderById == null && result is State.Error -> {
                    handleError(result.error) {
                        updateStateOf { copy(isLoading = false, error = result.error) }
                    }
                }

                else -> updateStateOf { copy(isLoading = false, orderUiModel = orderById) }
            }
        }
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