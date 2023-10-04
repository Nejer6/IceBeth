package com.example.icebeth.features.auth.data

import android.content.SharedPreferences
import com.example.icebeth.features.auth.data.remote.AuthApi
import com.example.icebeth.features.auth.data.remote.response.LoginResponse
import com.example.icebeth.common.util.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val prefs: SharedPreferences
) {
    suspend fun login(login: String, password: String): ApiResponse<LoginResponse> {
        return authApi.login(login, password).apply {
            when (this) {
                is ApiResponse.Success -> {
                    prefs.edit()
                        .putString("jwt", this.body.accessToken)
                        .apply()
                }
                else -> {}
            }
        }
    }

    suspend fun authenticate() = authApi.authenticate()
}