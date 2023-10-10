package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.icebeth.feature.add_measurement.navigation.addMeasurementScreen
import com.example.icebeth.feature.add_measurement.navigation.navigateToAddMeasurement
import com.example.icebeth.feature.login.navigation.loginScreen
import com.example.icebeth.feature.login.navigation.navigateToLogin
import com.example.icebeth.feature.splash.navigation.splashRoute
import com.example.icebeth.feature.splash.navigation.splashScreen

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
            logout = {
                navController.popBackStack()
                navController.navigateToLogin(navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                })
            },
            navigateToAddMeasurement = navController::navigateToAddMeasurement
        )

        loginScreen(
            onLogin = {
                navController.popBackStack()
                navController.navigateToMainGraph(navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                })
            }
        )

        addMeasurementScreen(
            navController::navigateUp
        )
    }
}