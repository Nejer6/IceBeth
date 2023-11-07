package com.example.icebeth.feature.login

import com.example.icebeth.common.presentation.ViewEvent
import com.example.icebeth.common.presentation.ViewState
import com.example.icebeth.core.domain.util.AuthError

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
