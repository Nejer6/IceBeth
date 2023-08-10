package com.example.icebeth.features.auth.domain.use_case

import com.example.icebeth.features.auth.data.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.authenticate()
}