package org.saham.fooddelivery.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.saham.fooddelivery.features.splash_screen.graph.SplashScreen
import org.saham.fooddelivery.features.splash_screen.graph.splashGraph


@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen::class) {
        splashGraph()
    }
}