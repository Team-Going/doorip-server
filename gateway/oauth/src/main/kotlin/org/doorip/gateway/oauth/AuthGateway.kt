package org.doorip.gateway.oauth

import org.doorip.domain.entity.AuthPlatform
import org.doorip.domain.repository.AuthRepository
import org.springframework.stereotype.Component

@Component
internal class AuthGateway : AuthRepository {

    override fun getPlatformId(token: String, platform: AuthPlatform): String {
        // TODO

        return token
    }
}
