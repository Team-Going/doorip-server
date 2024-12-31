package org.doorip.api.auth.dto

import org.doorip.domain.entity.UserInfo

data class AuthSignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val isResult: Boolean,
    val userId: Long,
)

fun UserInfo.toResponse(): AuthSignInResponse = AuthSignInResponse(
    accessToken = token.accessToken,
    refreshToken = token.refreshToken,
    isResult = user.result != null,
    userId = user.id.value,
)
