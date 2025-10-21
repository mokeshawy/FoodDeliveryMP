package org.saham.fooddelivery.core.background_services

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class BackgroundServiceAndroid(private val context: Context) : BackgroundService {
    override fun start() {
        val intent = Intent(context, LocationLifecycleService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    override fun stop() {
        val intent = Intent(context, LocationLifecycleService::class.java)
        context.stopService(intent)
    }
}