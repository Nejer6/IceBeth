package com.example.icebeth.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.domain.LoginUseCase
import com.example.icebeth.common.presentation.BaseViewModel
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.data.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    connectivityObserver: ConnectivityObserver
) :
    BaseViewModel<LoginContract.Event, LoginContract.State, UiEffect>() {

    var isForbidden by mutableStateOf(false)
        private set

    val connectStateFlow = connectivityObserver.observe()

    var loading by mutableStateOf(false)
        private set

    override fun setInitialState() = LoginContract.State()

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.SetLogin -> {
                isForbidden = false
                setState {
                    copy(
                        login = event.value,
                        loginError = null
                    )
                }
            }

            is LoginContract.Event.SetPassword -> {
                isForbidden = false
                setState {
                    copy(
                        password = event.value,
                        passwordError = null
                    )
                }
            }

            LoginContract.Event.Submit -> submitData()
        }
    }

    private fun submitData() {
        viewModelScope.launch {
            loading = true
            val loginResult = loginUseCase(viewState.value.login, viewState.value.password)
            loading = false

            setState {
                copy(
                    loginError = loginResult.loginError,
                    passwordError = loginResult.passwordError
                )
            }

            val result = loginResult.result
            if (result != null) {
                when (result) {
                    is ApiResponse.Error.Http -> {
                        when (result.status) {
                           HttpStatusCode.BadRequest -> {
                               isForbidden = true
                           }
                        }
                    }
                    ApiResponse.Error.Network -> setEffect {
                        UiEffect.ShowSnackbar(
                            "Не удается подключиться к серверу. Повторите попытку позже."
                        )
                    }
                    is ApiResponse.Success -> setEffect {
                        UiEffect.OnLogin
                    }
                }
            }
        }
    }
}