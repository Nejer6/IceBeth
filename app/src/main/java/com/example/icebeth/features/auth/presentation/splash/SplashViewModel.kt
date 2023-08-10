package com.example.icebeth.features.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.auth.domain.use_case.AuthenticateUseCase
import com.example.icebeth.shared.presentation.util.AppRoute
import com.example.icebeth.shared.presentation.util.UiEffect
import com.example.icebeth.shared.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEffect>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when (authenticateUseCase()) {
                is ApiResponse.Success -> {
                    _eventFlow.emit(
                        UiEffect.Navigate(AppRoute.MainRoute.route)
                    )
                }
                else -> {
                    _eventFlow.emit(
                        UiEffect.Navigate(AppRoute.LoginScreen.route)
                    )
                }
            }
        }
    }
}