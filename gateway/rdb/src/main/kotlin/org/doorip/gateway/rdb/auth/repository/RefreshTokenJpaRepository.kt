package org.doorip.gateway.rdb.auth.repository

import org.doorip.domain.user.UserId
import org.doorip.gateway.rdb.auth.entity.RefreshTokenJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenJpaEntity, ByteArray> {
    fun deleteByUserId(userId: UserId)
}
