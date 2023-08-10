package com.example.icebeth.features.auth.domain.util

sealed class AuthError {
    object FieldEmpty : AuthError()
}
