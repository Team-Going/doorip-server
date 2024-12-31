package org.doorip.gateway.oauth.kakao

import feign.FeignException
import org.doorip.domain.UnauthenticatedException
import org.springframework.stereotype.Component

@Component
internal class KakaoAuthService(
    private val httpClient: KakaoFeignClient,
) {

    fun getPlatformId(token: String): String {
        try {
            val kakaoAccessTokenInfo = httpClient.getKakaoAccessTokenInfo(HEADER_BEARER + token)

            return kakaoAccessTokenInfo.id.toString()
        } catch (ex: FeignException) {
            throw UnauthenticatedException
        }
    }

    companion object {
        private const val HEADER_BEARER = "Bearer "
    }
}
