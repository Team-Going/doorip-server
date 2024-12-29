package org.doorip.domain.repository

import java.time.temporal.TemporalAmount
import org.doorip.domain.entity.UserId

interface AccessTokenRepository {
    fun createAccessToken(userId: UserId, expiredTime: TemporalAmount): String
}
