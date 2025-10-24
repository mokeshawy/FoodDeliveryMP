package org.saham.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.saham.fooddelivery.core.platform_koin_config.initKoin
import org.saham.fooddelivery.koin_app.KoinApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initKoin(application)
        setContent {
            KoinApp()
        }
    }
}