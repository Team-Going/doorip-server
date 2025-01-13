package org.doorip.domain.auth

import org.doorip.domain.user.UserId

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val userId: UserId,
)
