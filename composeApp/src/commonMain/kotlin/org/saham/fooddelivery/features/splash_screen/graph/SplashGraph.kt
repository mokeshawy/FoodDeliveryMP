package org.saham.fooddelivery.features.splash_screen.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.saham.fooddelivery.features.order_list_screen.graph.navigateToOrderListGraph
import org.saham.fooddelivery.features.splash_screen.presentation.SplashScreen


@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable<SplashScreen> {
        SplashScreen(onNavigateToOrderList = navController::navigateToOrderListGraph)
    }
}