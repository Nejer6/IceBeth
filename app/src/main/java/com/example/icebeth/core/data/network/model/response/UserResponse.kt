package com.example.icebeth.core.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    @SerialName("full_name") val fullName: String,
    val id: Int,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("is_superuser") val isSuperuser: Boolean,
    val station: String
)
