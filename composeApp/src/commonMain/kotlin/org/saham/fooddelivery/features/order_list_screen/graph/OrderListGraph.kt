package org.saham.fooddelivery.features.order_list_screen.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable
import org.saham.fooddelivery.features.order_details.graph.navigateToOrderDetailsGraph
import org.saham.fooddelivery.features.order_details.graph.orderDetailsGraph
import org.saham.fooddelivery.features.order_list_screen.presentation.OrdersListScreen
import org.saham.fooddelivery.nav_host.RootGraph

@Serializable
data object OrderListGraph

@Serializable
data object OrderListScreen

fun NavGraphBuilder.orderListGraph(
    navController: NavController,
) {
    navigation<OrderListGraph>(
        startDestination = OrderListScreen::class,
    ) {
        composable<OrderListScreen> {
            OrdersListScreen(
                onNavigateToOrderDetails = { orderId ->
                    navController.navigateToOrderDetailsGraph(orderId = orderId)
                })
        }

        orderDetailsGraph(navController = navController)
    }
}

fun NavController.navigateToOrderListGraph() {
    navigate(route = OrderListGraph) {
        popUpTo(route = RootGraph) { inclusive = true }
    }
}