package org.doorip.domain.user

import org.doorip.domain.auth.Token

@JvmInline
value class UserId(
    val value: Long,
)

data class User(
    val id: UserId,
    val name: String,
    val intro: String,
    val result: Int?,
)

data class UserInfo(
    val user: User,
    val token: Token,
)
