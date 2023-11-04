package com.example.icebeth.core.domain.util

sealed class ResultError {
    data object Empty : ResultError()
    data object NegativeNumber : ResultError()
    data object MoreThan10 : ResultError()

    data object NotInt : ResultError()
}
