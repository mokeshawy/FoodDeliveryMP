package org.saham.fooddelivery.application

import android.app.Application
import org.saham.fooddelivery.core.background_services.initPlatformContext

class SahamApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initPlatformContext(context = this)
    }
}