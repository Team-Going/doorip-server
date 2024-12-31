package org.doorip.api.auth.dto

data class AuthSignUpRequest(
    val name: String,
    val intro: String,
    val platform: String,
)
