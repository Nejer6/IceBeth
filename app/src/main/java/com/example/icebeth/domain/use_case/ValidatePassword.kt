package com.example.icebeth.domain.use_case

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidatePassword @Inject constructor() {

    fun execute(password: String): ValidateResult {
        if (password.isBlank()) {
            return ValidateResult(
                false,
                "Введите пароль"
            )
        }
        return ValidateResult(
            true
        )
    }
}