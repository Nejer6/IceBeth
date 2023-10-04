package com.example.icebeth.features.auth.domain.models

import com.example.icebeth.features.auth.data.remote.response.LoginResponse
import com.example.icebeth.features.auth.domain.util.AuthError
import com.example.icebeth.common.util.ApiResponse

data class LoginResult(
    val loginError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: ApiResponse<LoginResponse>? = null
)
