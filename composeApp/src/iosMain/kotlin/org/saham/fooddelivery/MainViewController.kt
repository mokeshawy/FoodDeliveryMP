package org.saham.fooddelivery

import androidx.compose.ui.window.ComposeUIViewController
import org.saham.fooddelivery.koin_app.KoinApp

fun MainViewController() = ComposeUIViewController { KoinApp() }