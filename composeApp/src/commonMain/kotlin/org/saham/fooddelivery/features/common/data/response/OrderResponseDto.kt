package org.saham.fooddelivery.features.common.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OrderResponseDto(
    @SerialName("customerName")
    val customerName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("restaurant")
    val restaurant: String,
    @SerialName("status")
    val status: String
)