package org.doorip.gateway.rdb.user

import org.doorip.domain.auth.AuthPlatform
import org.doorip.domain.user.User
import org.doorip.domain.user.UserId
import org.doorip.domain.user.UserRepository
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

    override fun withdraw(userId: UserId) {
        userJpaRepository.deleteById(userId.value)
    }
}
