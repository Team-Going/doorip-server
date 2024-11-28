package org.doorip.core.auth

import org.doorip.domain.UserId

interface AccessTokenUseCase {
    fun getUserId(token: String): UserId?
}
