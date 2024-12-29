package org.doorip.gateway.rdb.token

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.doorip.domain.entity.UserId

@Table(name = "refresh_token")
@Entity
internal class RefreshTokenJpaEntity {
    @Id
    @Column(name = "refresh_token", columnDefinition = "binary(45)", nullable = false)
    lateinit var refreshToken: ByteArray

    @Column(name = "user_id", columnDefinition = "bigint", nullable = false)
    var userId: Long = 0

    @Column(name = "expired_at", columnDefinition = "datetime", nullable = false)
    lateinit var expiredAt: LocalDateTime

    companion object {
        fun of(
            refreshToken: ByteArray,
            userId: UserId,
            expiredAt: LocalDateTime,
        ): RefreshTokenJpaEntity = RefreshTokenJpaEntity().apply {
            this.refreshToken = refreshToken
            this.userId = userId.value
            this.expiredAt = expiredAt
        }
    }
}
