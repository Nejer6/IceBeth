package com.example.icebeth.feature.login

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.core.data.util.ConnectivityObserver
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreenDestination(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val connectState by viewModel.connectStateFlow
        .collectAsState(ConnectivityObserver.Status.Unavailable)

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.effect.onEach {
            when (it) {
                UiEffect.OnLogin -> onLogin()
                is UiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = it.text,
                        withDismissAction = true,
                        duration = SnackbarDuration.Indefinite
                    )
                }
                else -> {}
            }
        }.collect()
    }

    LoginScreen(
        state = viewModel.viewState.value,
        isForbidden = viewModel.isForbidden,
        onEventSent = viewModel::setEvent,
        connectState = connectState,
        snackbarHostState = snackbarHostState,
        loading = viewModel.loading
    )
}
