package com.example.icebeth.ui.screens.login

import com.example.icebeth.core.domain.util.AuthError
import com.example.icebeth.ui.util.ViewEvent
import com.example.icebeth.ui.util.ViewState

class LoginContract {

    data class State(
        val login: String = "",
        val loginError: AuthError? = null,
        val password: String = "",
        val passwordError: AuthError? = null
    ) : ViewState

    sealed class Event : ViewEvent {
        data class SetLogin(val value: String) : Event()
        data class SetPassword(val value: String) : Event()
        object Submit : Event()
    }
}
