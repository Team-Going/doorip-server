package org.doorip.api.auth.dto

import org.doorip.domain.entity.Token

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
)

fun Token.toResponse(): AuthResponse = AuthResponse(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken,
    userId = this.userId.value,
)
