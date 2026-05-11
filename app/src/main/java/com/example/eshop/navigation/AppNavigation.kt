package com.example.eshop.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eshop.screens.CartScreen
import com.example.eshop.screens.CheckoutScreen
import com.example.eshop.screens.HomeScreen
import com.example.eshop.screens.OrderConfirmationScreen
import com.example.eshop.screens.ProductDetailScreen
import com.example.eshop.viewmodel.ShopViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object OrderConfirmation : Screen("order_confirmation/{orderNumber}") {
        fun createRoute(orderNumber: String) = "order_confirmation/$orderNumber"
    }
}

@Composable
fun EShopApp() {
    val navController = rememberNavController()
    val viewModel: ShopViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val product = viewModel.products.find { it.id == productId } ?: return@composable
            ProductDetailScreen(
                product = product,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate(Screen.Checkout.route) }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOrderPlaced = { orderNumber ->
                    navController.navigate(Screen.OrderConfirmation.createRoute(orderNumber)) {
                        popUpTo(Screen.Home.route)
                    }
                }
            )
        }

        composable(
            route = Screen.OrderConfirmation.route,
            arguments = listOf(navArgument("orderNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderNumber = backStackEntry.arguments?.getString("orderNumber") ?: "000000"
            OrderConfirmationScreen(
                orderNumber = orderNumber,
                onContinueShopping = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
