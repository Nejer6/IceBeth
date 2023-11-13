package com.example.icebeth.ui.util

sealed class UiEffect : ViewSideEffect {
    data class ShowSnackbar(val text: String) : UiEffect()
    data object NavigateUp : UiEffect()
    data object OnLogin : UiEffect()
    data object Logout : UiEffect()
    data object NavigateToMainScreen : UiEffect()
}
