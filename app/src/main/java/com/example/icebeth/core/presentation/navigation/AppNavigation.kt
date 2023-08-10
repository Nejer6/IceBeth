package com.example.icebeth.core.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icebeth.features.auth.presentation.login.LoginScreenDestination
import com.example.icebeth.features.auth.presentation.splash.SplashScreenDestination
import com.example.icebeth.shared.presentation.util.Screen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreenDestination(onNavigate = {
                navController.popBackStack()
                navController.navigate(it)
            })
        }

        composable(Screen.LoginScreen.route) {
            LoginScreenDestination(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }

        composable(Screen.MainScreen.route) {
            Text(text = "Main")
        }
    }
}