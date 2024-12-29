package org.doorip.gateway.rdb.token

import java.security.SecureRandom
import java.time.LocalDateTime
import java.time.temporal.TemporalAmount
import java.util.*
import org.doorip.domain.UnauthenticatedException
import org.doorip.domain.entity.UserId
import org.doorip.domain.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class RefreshTokenGateway(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository,
) : RefreshTokenRepository {
    private val decoder: Base64.Decoder = Base64.getDecoder()
    private val encoder: Base64.Encoder = Base64.getEncoder()
    private val secureRandom: SecureRandom = SecureRandom()

    override fun getUserId(refreshToken: String): UserId? {
        val now = LocalDateTime.now()

        val decodedRefreshToken = decoder.decode(refreshToken)

        val foundRefreshToken = refreshTokenJpaRepository.findByIdOrNull(decodedRefreshToken) ?: return null

        if (foundRefreshToken.expiredAt.isBefore(now)) throw UnauthenticatedException

        return UserId(foundRefreshToken.userId)
    }

    override fun createRefreshToken(userId: UserId, expiredTime: TemporalAmount): String {
        val now = LocalDateTime.now()

        val byteRefreshToken = ByteArray(TOKEN_BYTE_SIZE).apply {
            secureRandom.nextBytes(this)
        }

        val refreshToken = RefreshTokenJpaEntity.of(
            refreshToken = byteRefreshToken,
            userId = userId,
            expiredAt = now.plus(expiredTime),
        )

        val savedRefreshToken = refreshTokenJpaRepository.save(refreshToken)

        return encoder.encodeToString(savedRefreshToken.refreshToken)
    }

    override fun deleteRefreshToken(userId: UserId) {
        refreshTokenJpaRepository.deleteByUserId(userId)
    }

    companion object {
        private const val TOKEN_BYTE_SIZE = 60 * 6 / 8 // 45 bytes
    }
}