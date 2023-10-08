package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.icebeth.feature.login.LoginScreenDestination
import com.example.icebeth.feature.splash.SplashScreenDestination
import com.example.icebeth.features.measurements.presentation.add_measurement.AddMeasurementRoute
import com.example.icebeth.feature.measurements.MainRoute
import com.example.icebeth.common.presentation.util.AppRoute

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
            "${AppRoute.AddMeasurementScreen.route}/{resultId}?measurement={measurement}",
            arguments = listOf(
                navArgument("resultId") { type = NavType.IntType},
                navArgument("measurement") { nullable = true }
            )
        ) {
            AddMeasurementRoute(
                navigateUp = navController::navigateUp
            )
        }

        composable(
            "${AppRoute.MeasurementsScreen.route}/{resultId}/{number}",
            arguments = listOf(
                navArgument("resultId") {
                    type = NavType.IntType
                },
                navArgument("number") {
                    type = NavType.IntType
                }
            )
        ) {
            MainRoute(
                navigate = navController::navigate,
                navigateUp = navController::navigateUp
            )
        }
    }
}