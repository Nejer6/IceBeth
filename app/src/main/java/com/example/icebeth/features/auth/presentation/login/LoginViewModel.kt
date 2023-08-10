package com.example.icebeth.features.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.auth.domain.use_case.LoginUseCase
import com.example.icebeth.shared.presentation.BaseViewModel
import com.example.icebeth.shared.presentation.util.UiEffect
import com.example.icebeth.shared.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) :
    BaseViewModel<LoginContract.Event, LoginContract.State, UiEffect>() {
    override fun setInitialState() = LoginContract.State()

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.SetLogin -> setState { copy(login = event.value) }
            is LoginContract.Event.SetPassword -> setState { copy(password = event.value) }
            LoginContract.Event.Submit -> submitData()
        }
    }

    private fun submitData() {
        viewModelScope.launch {
            val loginResult = loginUseCase(viewState.value.login, viewState.value.password)

            setState {
                copy(
                    loginError = loginResult.loginError,
                    passwordError = loginResult.passwordError
                )
            }

            val result = loginResult.result
            if (result != null) {
                when (result) {
                    is ApiResponse.Error.Http -> TODO()
                    ApiResponse.Error.Network -> TODO()
                    is ApiResponse.Success -> setEffect {
                        UiEffect.OnLogin
                    }
                }
            }
        }
    }
}