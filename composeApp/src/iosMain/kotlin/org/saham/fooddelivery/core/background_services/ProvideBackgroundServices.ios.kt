package org.saham.fooddelivery.core.background_services

actual fun provideBackgroundService(): BackgroundService {
    return BackgroundServiceIos()
}