package org.doorip.gateway.oauth.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["org.doorip"])
@Configuration
internal class OpenFeignConfig
