package org.doorip.gateway.rdb.user

import org.doorip.domain.entity.AuthPlatform
import org.doorip.domain.entity.UserId
import org.springframework.data.jpa.repository.JpaRepository

internal interface UserJpaRepository : JpaRepository<UserJpaEntity, UserId> {
    fun findByPlatformAndPlatformId(platform: AuthPlatform, platformId: String): UserJpaEntity?
}
