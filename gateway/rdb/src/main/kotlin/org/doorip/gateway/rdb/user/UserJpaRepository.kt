package org.doorip.gateway.rdb.user

import org.doorip.domain.auth.AuthPlatform
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

internal interface UserJpaRepository : JpaRepository<UserJpaEntity, Long> {
    fun findByPlatformAndPlatformId(platform: AuthPlatform, platformId: String): UserJpaEntity?
    fun existsByPlatformAndPlatformId(platform: AuthPlatform, platformId: String): Boolean

    @Modifying(clearAutomatically = true)
    @Query(
        "UPDATE UserJpaEntity u " +
            "SET u.name = :name, u.intro = :intro " +
            "WHERE u.id = :id",
    )
    fun updateNameAndIntroByUserId(@Param("id") userId: Long, @Param("name") name: String, @Param("intro") intro: String)
}
