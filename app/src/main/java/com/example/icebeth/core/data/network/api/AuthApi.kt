package com.example.icebeth.core.data.network.api

import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.common.util.safeRequest
import com.example.icebeth.core.data.network.model.response.LoginResponse
import com.example.icebeth.core.data.network.model.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.plugin
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
        return httpClient.safeRequest<LoginResponse> {
            post("login/access-token") {
                body = "grant_type=&username=$email&" +
                    "password=$password&scope=&client_id=&client_secret="
                headers.appendAll(
                    headersOf(
                        "accept" to listOf("application/json"),
                        "Content-type" to listOf("application/x-www-form-urlencoded")
                    )
                )
            }
        }.apply {
            when (val response = this) {
                is ApiResponse.Success -> {
                    httpClient.plugin(Auth).bearer {
                        loadTokens {
                            BearerTokens(response.body.accessToken, "")
                        }
                    }
                }
                else -> {}
            }
        }
    }

    suspend fun authenticate(): ApiResponse<UserResponse> =
        httpClient.safeRequest {
            post("login/test-token")
        }
}
