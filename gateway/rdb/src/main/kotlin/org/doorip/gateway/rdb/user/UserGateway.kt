package org.doorip.gateway.rdb.user

import org.doorip.domain.auth.AuthPlatform
import org.doorip.domain.user.User
import org.doorip.domain.user.UserId
import org.doorip.domain.user.UserRepository
import org.doorip.gateway.rdb.user.entity.UserJpaEntity
import org.doorip.gateway.rdb.user.entity.toDomain
import org.doorip.gateway.rdb.user.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserGateway(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun create(platformId: String, platform: AuthPlatform, name: String, intro: String): User? {
        if (userJpaRepository.existsByPlatformAndPlatformId(platform, platformId)) return null

        val user = UserJpaEntity.of(
            name = name,
            intro = intro,
            platformId = platformId,
            platform = platform,
        )

        val savedUser = userJpaRepository.save(user)

        return savedUser.toDomain()
    }

    override fun getUser(platformId: String, platform: AuthPlatform): User? {
        val findUser = userJpaRepository.findByPlatformAndPlatformId(platform, platformId) ?: return null

        return findUser.toDomain()
    }

    override fun getUser(userId: UserId): User? {
        val findUser = userJpaRepository.findByIdOrNull(userId.value) ?: return null

        return findUser.toDomain()
    }

    override fun updateUser(userId: UserId, name: String, intro: String) {
        userJpaRepository.updateNameAndIntroByUserId(
            userId = userId.value,
            name = name,
            intro = intro,
        )
    }

    override fun withdraw(userId: UserId) {
        userJpaRepository.deleteById(userId.value)
    }
}
