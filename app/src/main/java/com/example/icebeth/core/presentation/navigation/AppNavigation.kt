package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.icebeth.feature.active_result.navigation.activeResultScreen
import com.example.icebeth.feature.active_result.navigation.navigateToActiveResult
import com.example.icebeth.feature.login.navigation.loginScreen
import com.example.icebeth.feature.login.navigation.navigateToLogin
import com.example.icebeth.feature.splash.navigation.splashRoute
import com.example.icebeth.feature.splash.navigation.splashScreen

@Composable
fun AppNavigation(
    viewModel: AppViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = splashRoute) {
        splashScreen(
            onAuthorized = {
                navController.popBackStack()
                if (viewModel.activeResultId == null) {
                    navController.navigateToMainGraph()
                } else {
                    navController.navigateToActiveResult()
                }
            },
            onUnauthorized = {
                navController.popBackStack()
                navController.navigateToLogin()
            }
        )

        mainGraph(
            logout = {
                navController.popBackStack()
                navController.navigateToLogin()
            },
            navigateToActiveResult = {
                navController.popBackStack()
                navController.navigateToActiveResult()
            }
        )

        loginScreen(
            onLogin = {
                navController.popBackStack()
                navController.navigateToMainGraph()
            }
        )

        activeResultScreen(
            navigateToMain = {
                navController.popBackStack()
                navController.navigateToMainGraph()
            }
        )
    }
}