package org.doorip.domain.repository

import java.time.temporal.TemporalAmount
import org.doorip.domain.entity.UserId

interface RefreshTokenRepository {
    fun getUserId(refreshToken: String): UserId?
    fun createRefreshToken(userId: UserId, expiredTime: TemporalAmount): String
    fun deleteRefreshToken(userId: UserId)
}
