package org.saham.fooddelivery.features.common.domain.model.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderUiModel(
    @PrimaryKey val id: Int = 0,
    val customerName: String,
    val restaurant: String,
    val status: String
)
