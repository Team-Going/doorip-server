package org.doorip.api.user.dto

data class UserUpdateProfileRequest(
    val name: String,
    val intro: String,
)
