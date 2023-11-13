package com.example.icebeth.core.model

import com.example.icebeth.core.data.network.model.response.LoginResponse
import com.example.icebeth.core.data.network.util.ApiResponse
import com.example.icebeth.core.domain.util.AuthError

data class LoginResult(
    val loginError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: ApiResponse<LoginResponse>? = null
)
