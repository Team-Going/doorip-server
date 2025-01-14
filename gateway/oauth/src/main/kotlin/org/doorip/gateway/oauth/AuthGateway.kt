package org.doorip.gateway.oauth

import org.doorip.domain.auth.AuthPlatform
import org.doorip.domain.auth.AuthRepository
import org.doorip.gateway.oauth.apple.AppleAuthService
import org.doorip.gateway.oauth.kakao.KakaoAuthService
import org.springframework.stereotype.Component

@Component
internal class AuthGateway(
    private val appleAuthService: AppleAuthService,
    private val kakaoAuthService: KakaoAuthService,
) : AuthRepository {

    override fun getPlatformId(token: String, platform: AuthPlatform): String = when (platform) {
        AuthPlatform.APPLE -> appleAuthService.getPlatformId(token)
        AuthPlatform.KAKAO -> kakaoAuthService.getPlatformId(token)
    }
}
