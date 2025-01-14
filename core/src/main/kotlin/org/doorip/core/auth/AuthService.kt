package org.doorip.core.auth

import java.time.Duration
import org.doorip.domain.AlreadyExistingUserException
import org.doorip.domain.UnauthenticatedException
import org.doorip.domain.auth.AccessTokenRepository
import org.doorip.domain.auth.AuthPlatform
import org.doorip.domain.auth.AuthRepository
import org.doorip.domain.auth.RefreshTokenRepository
import org.doorip.domain.auth.Token
import org.doorip.domain.user.UserId
import org.doorip.domain.user.UserInfo
import org.doorip.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class AuthService(
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : AuthUseCase {

    @Transactional
    override fun signIn(token: String, platform: String): UserInfo {
        val authPlatform = AuthPlatform.toAuthPlatform(platform)
        val platformId = authRepository.getPlatformId(token, authPlatform)

        val user = userRepository.getUser(
            platformId = platformId,
            platform = authPlatform,
        ) ?: throw UnauthenticatedException

        val userInfo = UserInfo(
            user = user,
            token = issueToken(user.id),
        )

        return userInfo
    }

    @Transactional
    override fun signUp(token: String, platform: String, name: String, intro: String): Token {
        val authPlatform = AuthPlatform.toAuthPlatform(platform)
        val platformId = authRepository.getPlatformId(token, authPlatform)

        val user = userRepository.create(
            platformId = platformId,
            platform = authPlatform,
            name = name,
            intro = intro,
        ) ?: throw AlreadyExistingUserException

        return issueToken(user.id)
    }

    override fun signOut(userId: UserId) {
        refreshTokenRepository.deleteRefreshToken(userId)
    }

    override fun reissue(refreshToken: String): Token {
        val userId = refreshTokenRepository.getUserId(refreshToken) ?: throw UnauthenticatedException

        return issueToken(userId)
    }

    @Transactional
    override fun withdraw(userId: UserId) {
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
