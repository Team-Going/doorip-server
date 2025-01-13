package org.doorip.core.auth

import org.doorip.domain.user.UserId

interface AccessTokenUseCase {
    fun getUserId(token: String): UserId?
}
