package com.example.icebeth.common.presentation.util

import com.example.icebeth.common.presentation.ViewSideEffect

sealed class UiEffect : ViewSideEffect {
    data class ShowSnackbar(val text: String) : UiEffect()
    data class Navigate(val route: String) : UiEffect()
    object NavigateUp : UiEffect()
    object OnLogin : UiEffect()
    object Logout : UiEffect()
}
