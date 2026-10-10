package com.cinevault.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTokenResponseDto(
    @SerialName("success") val success: Boolean,
    @SerialName("expires_at") val expiresAt: String? = null,
    @SerialName("request_token") val requestToken: String
)

@Serializable
data class CreateSessionRequestDto(
    @SerialName("request_token") val requestToken: String
)

@Serializable
data class CreateSessionResponseDto(
    @SerialName("success") val success: Boolean,
    @SerialName("session_id") val sessionId: String
)

@Serializable
data class CreateGuestSessionResponseDto(
    @SerialName("success") val success: Boolean,
    @SerialName("guest_session_id") val guestSessionId: String,
    @SerialName("expires_at") val expiresAt: String? = null
)
