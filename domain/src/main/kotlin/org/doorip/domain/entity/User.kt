package org.doorip.domain.entity

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
