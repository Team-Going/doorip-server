package org.doorip.domain.auth

import java.time.temporal.TemporalAmount
import org.doorip.domain.user.UserId

interface AccessTokenRepository {
    fun createAccessToken(userId: UserId, expiredTime: TemporalAmount): String
}
