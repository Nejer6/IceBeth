package com.example.icebeth.shared.util

import io.ktor.http.HttpStatusCode

sealed class ApiResponse<out T> {
    data class Success<T>(val body: T): ApiResponse<T>()

    sealed class Error : ApiResponse<Nothing>() {
        data class Http(val status: HttpStatusCode) : Error()

        object Network : Error()
    }
}
