package org.saham.fooddelivery.features.order_list_screen.domain.event

import com.shared.core.bases.base_viewmodel.ViewIntent

sealed class OrderListIntent : ViewIntent {

    data object GetOrdersListIntent : OrderListIntent()
}