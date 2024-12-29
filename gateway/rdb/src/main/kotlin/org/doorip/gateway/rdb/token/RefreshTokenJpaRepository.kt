package org.doorip.gateway.rdb.token

import org.doorip.domain.entity.UserId
import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenJpaEntity, ByteArray> {
    fun deleteByUserId(userId: UserId)
}
