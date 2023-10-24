package com.example.icebeth.common.util

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.statement.HttpResponse
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Performs a safe HTTP request using the HttpClient and handles potential network exceptions.
 *
 * @param block A lambda that defines the HTTP request using HttpClient.
 *
 * @return An ApiResponse representing the result of the HTTP request.
 */
suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpClient.() -> HttpResponse
): ApiResponse<T> = try {
    val response = block()
    when (response.status.value) {
        in 200..299 -> ApiResponse.Success(response.body<T>())
        else -> ApiResponse.Error.Http(response.status)
    }
} catch (e: ConnectTimeoutException) {
    ApiResponse.Error.Network
} catch (e: ConnectException) {
    ApiResponse.Error.Network
} catch (e: SocketTimeoutException) {
    ApiResponse.Error.Network
}