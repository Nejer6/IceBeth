package com.example.icebeth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
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
                if (viewModel.activeResultId == null) {
                    navController.navigateToMainGraph(navOptions {
                        popUpTo(splashRoute) {
                            inclusive = true
                        }
                    })
                } else {
                    navController.navigateToActiveResult(navOptions {
                        popUpTo(splashRoute) {
                            inclusive = true
                        }
                    })
                }
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
            navigateToActiveResult = {
                navController.popBackStack()
                navController.navigateToActiveResult()
            }
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

        activeResultScreen(
            navigateToMain = {
                navController.navigateToMainGraph(navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                })
            }
        )
    }
}