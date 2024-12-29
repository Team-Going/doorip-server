package org.doorip.domain.entity

enum class AuthPlatform(
    private val description: String,
) {
    APPLE("애플"),
    KAKAO("카카오"),
    ;

    companion object {
        fun toAuthPlatform(authPlatform: String): AuthPlatform = AuthPlatform.valueOf(authPlatform.uppercase())
    }
}
