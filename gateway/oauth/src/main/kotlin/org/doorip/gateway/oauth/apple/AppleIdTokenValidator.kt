package org.doorip.gateway.oauth.apple

import org.doorip.domain.UnauthenticatedException
import org.doorip.support.jwt.JwtProvider
import org.doorip.support.jwt.SignatureKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
internal class AppleIdTokenValidator(
    private val jwtProvider: JwtProvider,
) {
    @Value("\${oauth.apple.iss}")
    lateinit var iss: String

    @Value("\${oauth.apple.client-id}")
    lateinit var clientId: String

    internal fun validateAndGetSubject(token: String, key: SignatureKey): String {
        return jwtProvider.validateAndGetSubject(
            token = token,
            key = key,
            iss = iss,
            aud = clientId,
        ) ?: throw UnauthenticatedException
    }
}
