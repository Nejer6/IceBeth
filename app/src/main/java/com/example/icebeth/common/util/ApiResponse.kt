package com.example.icebeth.common.util

import io.ktor.http.HttpStatusCode

/**
 * Represents the result of an API response, which can be a success or an error.
 *
 * @param T The type of data in the response body.
 */
sealed class ApiResponse<out T> {
    /**
     * Represents a successful API response with a response body.
     *
     * @property body The response body.
     */
    data class Success<T>(val body: T) : ApiResponse<T>()

    /**
     * Represents an error response, which can be an HTTP error or a network error.
     */
    sealed class Error : ApiResponse<Nothing>() {
        /**
         * Represents an HTTP error response with the HTTP status code.
         *
         * @property status The HTTP status code associated with the error.
         */
        data class Http(val status: HttpStatusCode) : Error()

        /**
         * Represents a network error response.
         */
        object Network : Error()
    }
}
