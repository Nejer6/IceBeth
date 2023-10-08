package com.example.icebeth.core.model.data

import com.example.icebeth.core.network.model.response.LoginResponse
import com.example.icebeth.core.domain.util.AuthError
import com.example.icebeth.common.util.ApiResponse

data class LoginResult(
    val loginError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: ApiResponse<LoginResponse>? = null
)
