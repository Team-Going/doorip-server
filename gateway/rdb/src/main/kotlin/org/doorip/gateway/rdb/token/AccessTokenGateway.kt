package org.doorip.gateway.rdb.token

import java.time.LocalDateTime
import java.time.temporal.TemporalAmount
import org.doorip.domain.entity.UserId
import org.doorip.domain.repository.AccessTokenRepository
import org.doorip.support.jwt.JwtProvider
import org.doorip.support.jwt.JwtSecretKey
import org.springframework.stereotype.Component

@Component
internal class AccessTokenGateway(
    private val jwtProvider: JwtProvider,
    private val jwtSecretKey: JwtSecretKey,
) : AccessTokenRepository {

    override fun createAccessToken(userId: UserId, expiredTime: TemporalAmount): String {
        val now = LocalDateTime.now()

        return jwtProvider.generate(
            id = userId.value.toString(),
            expiration = now.plus(expiredTime),
            key = jwtSecretKey,
        )
    }
}
