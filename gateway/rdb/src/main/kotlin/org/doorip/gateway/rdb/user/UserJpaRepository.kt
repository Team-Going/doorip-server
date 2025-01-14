package org.doorip.gateway.rdb.user

import org.doorip.domain.auth.AuthPlatform
import org.springframework.data.jpa.repository.JpaRepository

internal interface UserJpaRepository : JpaRepository<UserJpaEntity, Long> {
    fun findByPlatformAndPlatformId(platform: AuthPlatform, platformId: String): UserJpaEntity?
    fun existsByPlatformAndPlatformId(platform: AuthPlatform, platformId: String): Boolean
}
