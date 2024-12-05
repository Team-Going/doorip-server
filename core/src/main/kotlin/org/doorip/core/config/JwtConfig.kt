package org.doorip.core.config

import org.doorip.support.jwt.JwtProvider
import org.doorip.support.jwt.JwtSecretKey
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
internal class JwtConfig {

    @Bean
    fun jwtProvider(): JwtProvider = JwtProvider()

    @Bean
    fun jwtSecretKey(jwtProperties: JwtProperties): JwtSecretKey = JwtSecretKey(jwtProperties.secret)
}
