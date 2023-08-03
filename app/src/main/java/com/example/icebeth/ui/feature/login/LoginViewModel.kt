package com.example.icebeth.ui.feature.login

import com.example.icebeth.domain.use_case.ValidateLogin
import com.example.icebeth.domain.use_case.ValidatePassword
import com.example.icebeth.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLogin: ValidateLogin,
    private val validatePassword: ValidatePassword
) :
    BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    override fun setInitialState() = LoginContract.State()

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.SetLogin -> setState { copy(login = event.value) }
            is LoginContract.Event.SetPassword -> setState { copy(password = event.value) }
            LoginContract.Event.Submit -> submitData()
        }
    }

    private fun submitData() {
        val loginResult = validateLogin.execute(viewState.value.login)
        val passwordResult = validatePassword.execute(viewState.value.password)

        setState {
            copy(
                loginError = loginResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
        }

        val hasError = listOf(
            loginResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) return

    }
}