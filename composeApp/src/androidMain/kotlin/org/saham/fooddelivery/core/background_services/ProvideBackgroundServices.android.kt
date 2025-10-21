package org.saham.fooddelivery.core.background_services

import android.content.Context


private lateinit var appContext: Context

fun initPlatformContext(context: Context) {
    appContext = context.applicationContext
}

actual fun provideBackgroundService(): BackgroundService {
    return BackgroundServiceAndroid(context = appContext)
}