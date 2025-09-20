package org.saham.fooddelivery.features.order_details_screen.domain.event

import org.saham.fooddelivery.core.bases.base_viewmodel.ViewIntent

sealed class OrderDetailsIntent : ViewIntent {

    data class GetOrderDetailsByIdIntent(val orderId: Int) : OrderDetailsIntent()
}