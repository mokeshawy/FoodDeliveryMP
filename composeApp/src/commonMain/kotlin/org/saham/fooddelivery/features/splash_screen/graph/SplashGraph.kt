package org.saham.fooddelivery.features.splash_screen.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.saham.fooddelivery.features.splash_screen.presentation.SplashScreen
import org.saham.fooddelivery.nav_host.SplashScreen


fun NavGraphBuilder.splashGraph() {
    composable<SplashScreen> {
        SplashScreen(onNavigateToOrderList = {})
    }
}