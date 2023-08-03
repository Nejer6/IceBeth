package com.example.icebeth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.Routes.LOGIN) {
        composable(Navigation.Routes.LOGIN) {
            LoginScreenDestination(
                navController = navController
            )
        }
    }
}

object Navigation {
    object Routes {
        const val LOGIN = "login"
    }
}