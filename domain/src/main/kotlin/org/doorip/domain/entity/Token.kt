package org.doorip.domain.entity

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val userId: UserId,
)
