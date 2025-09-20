package org.saham.fooddelivery.core.error

interface AppErrorHandler {
    fun handleError(error: AppError, callback: AppError.() -> Unit = {})
}