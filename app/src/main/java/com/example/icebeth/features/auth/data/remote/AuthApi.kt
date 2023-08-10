package com.example.icebeth.features.auth.data.remote

import com.example.icebeth.features.auth.data.remote.response.LoginResponse
import com.example.icebeth.features.auth.data.remote.response.UserResponse
import com.example.icebeth.shared.util.ApiResponse
import com.example.icebeth.shared.util.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.headersOf
import io.ktor.util.InternalAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthApi @Inject constructor(
    private val httpClient: HttpClient
) {

    @OptIn(InternalAPI::class)
    suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return httpClient.safeRequest {
            post("login/access-token") {
                body = "grant_type=&username=${email}&password=${password}&scope=&client_id=&client_secret="
                headers.appendAll(headersOf(
                    "accept" to listOf("application/json"),
                    "Content-type" to listOf("application/x-www-form-urlencoded")
                ))
            }
        }
    }

    suspend fun authenticate(): ApiResponse<UserResponse> =
        httpClient.safeRequest {
            post("login/test-token")
        }
}