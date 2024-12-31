package org.doorip.gateway.oauth.apple

import feign.FeignException
import org.doorip.domain.UnauthenticatedException
import org.doorip.gateway.oauth.apple.dto.ApplePublicKeys
import org.doorip.support.jwt.JwtProvider
import org.doorip.support.jwt.RSAPublicKey
import org.doorip.support.jwt.SignatureKey
import org.springframework.stereotype.Component

@Component
internal class ApplePublicKeyGenerator(
    private val httpClient: AppleFeignClient,
    private val jwtProvider: JwtProvider,
) {

    internal fun generatePublicKey(token: String): SignatureKey {
        val applePublicKeys = getApplePublicKeys()

        val header = jwtProvider.parseHeader(token) ?: throw UnauthenticatedException

        val alg = header["alg"] ?: throw UnauthenticatedException
        val kid = header["kid"] ?: throw UnauthenticatedException
        val key = applePublicKeys[alg, kid] ?: throw UnauthenticatedException

        return RSAPublicKey(key.n, key.e, key.kty)
    }

    private fun getApplePublicKeys(): ApplePublicKeys {
        try {
            return httpClient.getApplePublicKeys()
        } catch (e: FeignException) {
            throw UnauthenticatedException
        }
    }
}
