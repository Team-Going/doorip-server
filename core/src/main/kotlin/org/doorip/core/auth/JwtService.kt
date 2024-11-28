package org.doorip.core.auth

import org.doorip.domain.UserId
import org.springframework.stereotype.Component

@Component
internal class JwtService : AccessTokenUseCase {

    override fun getUserId(token: String): UserId? {
        // TODO
        return null
    }
}
