package com.example.icebeth.core.data.repository

import android.content.SharedPreferences
import com.example.icebeth.core.network.model.api.AuthApi
import com.example.icebeth.core.network.model.response.LoginResponse
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

    fun isAuthorized(): Boolean {
        return (prefs.getString("jwt", "") ?: "") != ""
    }
}