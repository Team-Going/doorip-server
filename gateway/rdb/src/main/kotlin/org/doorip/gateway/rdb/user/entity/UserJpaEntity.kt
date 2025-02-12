package org.doorip.gateway.rdb.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.doorip.domain.auth.AuthPlatform
import org.doorip.domain.user.User
import org.doorip.domain.user.UserId
import org.doorip.gateway.rdb.BaseJpaEntity

@Table(name = "users")
@Entity
internal class UserJpaEntity : BaseJpaEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
    lateinit var name: String
        protected set

    @Column(name = "intro", columnDefinition = "varchar(255)", nullable = false)
    lateinit var intro: String
        protected set

    @Column(name = "result", columnDefinition = "integer", nullable = true)
    var result: Int? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", columnDefinition = "varchar(10)", nullable = false)
    lateinit var platform: AuthPlatform
        protected set

    @Column(name = "platform_id", columnDefinition = "varchar(255)", nullable = false)
    lateinit var platformId: String
        protected set

    companion object {
        fun of(
            name: String,
            intro: String,
            platformId: String,
            platform: AuthPlatform,
        ): UserJpaEntity = UserJpaEntity().apply {
            this.name = name
            this.intro = intro
            this.platform = platform
            this.platformId = platformId
        }
    }
}

internal fun UserJpaEntity.toDomain(): User = User(
    id = UserId(this.id),
    name = this.name,
    intro = this.intro,
    result = this.result,
)
