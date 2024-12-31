package org.doorip.gateway.oauth.kakao

import org.doorip.gateway.oauth.kakao.dto.KakaoAccessTokenInfo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakao-kakao-feign", url = "https://kapi.kakao.com/v1/user/access_token_info")
internal interface KakaoFeignClient {
    @GetMapping
    fun getKakaoAccessTokenInfo(@RequestHeader("Authorization") token: String): KakaoAccessTokenInfo
}
