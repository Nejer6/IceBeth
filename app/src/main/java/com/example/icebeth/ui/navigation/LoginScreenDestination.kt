package com.example.icebeth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.icebeth.ui.feature.login.LoginViewModel
import com.example.icebeth.ui.feature.login.composables.LoginScreen

@Composable
fun LoginScreenDestination(
    navController: NavController
) {
    val viewModel: LoginViewModel = hiltViewModel()
    LoginScreen(
        state = viewModel.viewState.value,
        onEventSent = viewModel::setEvent
    )
}