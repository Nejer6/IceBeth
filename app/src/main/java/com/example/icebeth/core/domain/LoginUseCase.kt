package com.example.icebeth.core.domain

import com.example.icebeth.core.data.repository.AuthRepository
import com.example.icebeth.core.model.LoginResult
import com.example.icebeth.core.domain.util.AuthError
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