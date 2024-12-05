package org.doorip.core.auth

import org.doorip.domain.UserId
import org.doorip.support.jwt.JwtProvider
import org.doorip.support.jwt.JwtSecretKey
import org.springframework.stereotype.Service

@Service
internal class JwtService(
    private val jwtProvider: JwtProvider,
    private val jwtSecretKey: JwtSecretKey,
) : AccessTokenUseCase {

    override fun getUserId(token: String): UserId? {
        val subject = jwtProvider.validateAndGetSubject(token, jwtSecretKey) ?: return null

        return UserId(subject.toLong())
    }
}
