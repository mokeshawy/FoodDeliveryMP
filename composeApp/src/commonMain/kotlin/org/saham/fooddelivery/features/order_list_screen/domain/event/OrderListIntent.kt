package org.saham.fooddelivery.features.order_list_screen.domain.event

import org.saham.fooddelivery.core.bases.base_viewmodel.ViewIntent

sealed class OrderListIntent : ViewIntent {

    data object GetOrdersListIntent : OrderListIntent()
}