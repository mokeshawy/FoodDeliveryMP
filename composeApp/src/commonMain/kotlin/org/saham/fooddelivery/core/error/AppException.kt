package org.saham.fooddelivery.core.error

data class AppException(val appError: AppError) : Exception()