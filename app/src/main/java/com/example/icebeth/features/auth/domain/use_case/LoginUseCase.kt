package com.example.icebeth.features.auth.domain.use_case

import com.example.icebeth.features.auth.data.remote.AuthRepository
import com.example.icebeth.features.auth.domain.models.LoginResult
import com.example.icebeth.features.auth.domain.util.AuthError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(login: String, password: String): LoginResult {
        val loginError = if (login.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null

        if (loginError != null || passwordError != null) {
            return LoginResult(loginError, passwordError)
        }

        return LoginResult(result = authRepository.login(login, password))
    }
}