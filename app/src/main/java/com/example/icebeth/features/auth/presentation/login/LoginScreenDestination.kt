package com.example.icebeth.features.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.util.UiEffect
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreenDestination(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.effect.onEach {
            when (it) {
                UiEffect.OnLogin -> onLogin()
                else -> {}
            }
        }.collect()
    }

    LoginScreen(
        state = viewModel.viewState.value,
        isForbidden = viewModel.isForbidden,
        onEventSent = viewModel::setEvent
    )
}