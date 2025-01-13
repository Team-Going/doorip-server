package org.doorip.domain.auth

interface AuthRepository {
    fun getPlatformId(token: String, platform: AuthPlatform): String
}
