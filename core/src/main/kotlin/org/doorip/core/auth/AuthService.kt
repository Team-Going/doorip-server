package org.doorip.core.auth

import java.time.Duration
import org.doorip.domain.UnauthenticatedException
import org.doorip.domain.entity.AuthPlatform
import org.doorip.domain.entity.Token
import org.doorip.domain.entity.UserId
import org.doorip.domain.repository.AccessTokenRepository
import org.doorip.domain.repository.AuthRepository
import org.doorip.domain.repository.RefreshTokenRepository
import org.doorip.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class AuthService(
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) {

    @Transactional(readOnly = true)
    fun signIn(token: String, platform: String): Token {
        val authPlatform = AuthPlatform.toAuthPlatform(platform)
        val platformId = authRepository.getPlatformId(token, authPlatform)

        val user = userRepository.getUser(
            platformId = platformId,
            platform = authPlatform,
        ) ?: throw UnauthenticatedException

        return issueToken(user.id)
    }

    @Transactional
    fun signUp(token: String, platform: String, name: String, intro: String): Token {
        val authPlatform = AuthPlatform.toAuthPlatform(platform)
        val platformId = authRepository.getPlatformId(token, authPlatform)

        val user = userRepository.create(
            platformId = platformId,
            platform = authPlatform,
            name = name,
            intro = intro,
        )

        return issueToken(user.id)
    }

    fun signOut(userId: UserId) {
        refreshTokenRepository.deleteRefreshToken(userId)
    }

    fun reissue(refreshToken: String): Token {
        val userId = refreshTokenRepository.getUserId(refreshToken) ?: throw UnauthenticatedException

        return issueToken(userId)
    }

    @Transactional
    fun withdraw(userId: UserId) {
        refreshTokenRepository.deleteRefreshToken(userId)
        userRepository.withdraw(userId)
    }

    private fun issueToken(userId: UserId): Token = Token(
        accessToken = accessTokenRepository.createAccessToken(
            userId = userId,
            expiredTime = Duration.ofMinutes(30),
        ),
        refreshToken = refreshTokenRepository.createRefreshToken(
            userId = userId,
            expiredTime = Duration.ofDays(30),
        ),
        userId = userId,
    )
}