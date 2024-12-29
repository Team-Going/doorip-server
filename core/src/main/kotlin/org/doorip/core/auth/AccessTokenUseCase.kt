package org.doorip.core.auth

import org.doorip.domain.entity.UserId

interface AccessTokenUseCase {
    fun getUserId(token: String): UserId?
}
