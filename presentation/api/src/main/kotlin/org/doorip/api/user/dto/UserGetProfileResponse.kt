package org.doorip.api.user.dto

import org.doorip.domain.user.User

data class UserGetProfileResponse(
    val name: String,
    val intro: String,
    val result: Int,
)

fun User.toResponse() = UserGetProfileResponse(
    name = name,
    intro = intro,
    result = if (result == null) -1 else result!!,
)
