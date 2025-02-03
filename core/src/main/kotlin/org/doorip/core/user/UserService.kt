package org.doorip.core.user

import org.doorip.domain.NotFoundException
import org.doorip.domain.user.User
import org.doorip.domain.user.UserId
import org.doorip.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class UserService(
    private val userRepository: UserRepository,
) : ProfileUseCase {

    override fun getProfile(userId: UserId): User {
        return userRepository.getUser(userId) ?: throw NotFoundException
    }

    @Transactional
    override fun updateProfile(userId: UserId, name: String, intro: String) {
        userRepository.updateUser(
            userId = userId,
            name = name,
            intro = intro,
        )
    }
}
