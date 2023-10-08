package com.example.icebeth.core.domain.util

sealed class AuthError {
    object FieldEmpty : AuthError()
}
