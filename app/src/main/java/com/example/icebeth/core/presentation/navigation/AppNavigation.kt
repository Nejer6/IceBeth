package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.icebeth.features.auth.presentation.login.LoginScreenDestination
import com.example.icebeth.features.auth.presentation.splash.SplashScreenDestination
import com.example.icebeth.features.measurements.presentation.add_measurement.AddMeasurementRoute
import com.example.icebeth.shared.presentation.util.AppRoute

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoute.SplashScreen.route) {
        composable(AppRoute.SplashScreen.route) {
            SplashScreenDestination(onNavigate = {
                navController.popBackStack()
                navController.navigate(it)
            })
        }

        composable(AppRoute.MainRoute.route) {
            MainNavigation(
                navigate = navController::navigate,
                logout = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.LoginScreen.route)
                }
            )
        }

        composable(AppRoute.LoginScreen.route) {
            LoginScreenDestination(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.MainRoute.route)
                }
            )
        }

        composable(
            "${AppRoute.AddMeasurementScreen.route}?measurement={measurement}",
            arguments = listOf(
                navArgument("measurement") { nullable = true }
            )
        ) {
            AddMeasurementRoute(
                navigateUp = navController::navigateUp
            )
        }
    }
}