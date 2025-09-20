package org.saham.fooddelivery.features.splash_screen.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.saham.fooddelivery.features.splash_screen.presentation.SplashScreen


@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph() {
    composable<SplashScreen> {
        SplashScreen(onNavigateToOrderList = {})
    }
}