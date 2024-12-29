package org.doorip.domain.repository

import org.doorip.domain.entity.AuthPlatform

interface AuthRepository {
    fun getPlatformId(token: String, platform: AuthPlatform): String
}
