package com.example.icebeth.domain.use_case

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateLogin @Inject constructor() {

    fun execute(login: String): ValidateResult {
        if (login.isBlank()) {
            return ValidateResult(
                false,
                "Введите логин"
            )
        }
        return ValidateResult(
            true
        )
    }
}