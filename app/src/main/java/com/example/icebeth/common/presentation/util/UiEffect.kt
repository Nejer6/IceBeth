package com.example.icebeth.common.presentation.util

import com.example.icebeth.common.presentation.ViewSideEffect

sealed class UiEffect : ViewSideEffect {
    data class ShowSnackbar(val text: String) : UiEffect()
    data object NavigateUp : UiEffect()
    data object OnLogin : UiEffect()
    data object Logout : UiEffect()
    data object NavigateToMainScreen : UiEffect()
}
