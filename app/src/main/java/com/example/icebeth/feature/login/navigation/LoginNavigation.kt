package com.example.icebeth.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val loginRoute = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(loginRoute, navOptions)
}