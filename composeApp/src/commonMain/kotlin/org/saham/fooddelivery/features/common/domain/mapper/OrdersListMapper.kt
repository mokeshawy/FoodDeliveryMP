package org.saham.fooddelivery.features.common.domain.mapper

import org.saham.fooddelivery.features.common.data.response.OrderResponseDto
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel


fun OrderResponseDto.toOrderUiModel() = OrderUiModel(
    customerName = customerName,
    id = id,
    restaurant = restaurant,
    status = status,
)