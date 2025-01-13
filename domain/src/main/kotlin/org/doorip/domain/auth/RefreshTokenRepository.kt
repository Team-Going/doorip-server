package org.doorip.domain.auth

import java.time.temporal.TemporalAmount
import org.doorip.domain.user.UserId

interface RefreshTokenRepository {
    fun getUserId(refreshToken: String): UserId?
    fun createRefreshToken(userId: UserId, expiredTime: TemporalAmount): String
    fun deleteRefreshToken(userId: UserId)
}
