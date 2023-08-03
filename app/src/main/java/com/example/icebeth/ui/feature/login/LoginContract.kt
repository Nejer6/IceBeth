package com.example.icebeth.ui.feature.login

import com.example.icebeth.ui.base.ViewEvent
import com.example.icebeth.ui.base.ViewSideEffect
import com.example.icebeth.ui.base.ViewState

class LoginContract {

    data class State(
        val login: String = "",
        val loginError: String? = null,
        val password: String = "",
        val passwordError: String? = null
    ) : ViewState

    sealed class Event : ViewEvent {
        data class SetLogin(val value: String) : Event()
        data class SetPassword(val value: String) : Event()
        object Submit : Event()
    }

    sealed class Effect : ViewSideEffect
}