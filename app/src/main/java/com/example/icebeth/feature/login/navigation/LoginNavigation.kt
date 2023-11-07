package com.example.icebeth.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.icebeth.feature.login.LoginScreenDestination

const val loginRoute = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLogin: () -> Unit
) {
    composable(loginRoute) {
        LoginScreenDestination(
            onLogin = onLogin
        )
    }
}
