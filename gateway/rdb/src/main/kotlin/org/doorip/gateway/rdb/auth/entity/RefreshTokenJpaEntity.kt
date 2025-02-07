package org.doorip.gateway.rdb.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.doorip.domain.user.UserId
import org.doorip.gateway.rdb.BaseJpaEntity

@Table(name = "refresh_token")
@Entity
internal class RefreshTokenJpaEntity : BaseJpaEntity() {

    @Id
    @Column(name = "refresh_token", columnDefinition = "binary(45)", nullable = false)
    lateinit var refreshToken: ByteArray
        protected set

    @Column(name = "user_id", columnDefinition = "bigint", nullable = false)
    var userId: Long = 0
        protected set

    @Column(name = "expired_at", columnDefinition = "datetime", nullable = false)
    lateinit var expiredAt: LocalDateTime
        protected set

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
