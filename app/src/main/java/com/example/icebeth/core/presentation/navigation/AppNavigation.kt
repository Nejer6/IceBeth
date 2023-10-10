package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.icebeth.common.presentation.util.AppRoute
import com.example.icebeth.feature.login.LoginScreenDestination
import com.example.icebeth.feature.login.navigation.loginRoute
import com.example.icebeth.feature.login.navigation.navigateToLogin
import com.example.icebeth.feature.measurements.MainRoute
import com.example.icebeth.feature.splash.navigation.splashRoute
import com.example.icebeth.feature.splash.navigation.splashScreen
import com.example.icebeth.features.measurements.presentation.add_measurement.navigation.addMeasurementScreen
import com.example.icebeth.features.measurements.presentation.add_measurement.navigation.navigateToAddMeasurement

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = splashRoute) {
        splashScreen(
            onAuthorized = {
                navController.navigateToMainGraph(navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                })
            },
            onUnauthorized = {
                navController.navigateToLogin(navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                })
            }
        )

        mainGraph(
            navigate = navController::navigate,
            logout = {
                navController.popBackStack()
                navController.navigate(AppRoute.LoginScreen.route)
            },
            navigateToAddMeasurement = navController::navigateToAddMeasurement
        )

        composable(loginRoute) {
            LoginScreenDestination(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.MainRoute.route)
                }
            )
        }

        addMeasurementScreen(
            navController::navigateUp
        )

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