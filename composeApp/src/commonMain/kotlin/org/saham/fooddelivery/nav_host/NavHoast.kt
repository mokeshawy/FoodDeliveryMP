package org.saham.fooddelivery.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.saham.fooddelivery.features.order_list_screen.graph.orderListGraph
import org.saham.fooddelivery.features.splash_screen.graph.SplashScreen
import org.saham.fooddelivery.features.splash_screen.graph.splashGraph

@Serializable
data object RootGraph

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen::class,
        route = RootGraph::class
    ) {
        splashGraph(navController = navController)

        orderListGraph(navController = navController)
    }
}