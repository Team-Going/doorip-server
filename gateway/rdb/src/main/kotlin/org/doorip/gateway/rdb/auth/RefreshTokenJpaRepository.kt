package org.doorip.gateway.rdb.auth

import org.doorip.domain.user.UserId
import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenJpaEntity, ByteArray> {
    fun deleteByUserId(userId: UserId)
}
