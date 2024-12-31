package org.doorip.domain.repository

import org.doorip.domain.entity.AuthPlatform
import org.doorip.domain.entity.User
import org.doorip.domain.entity.UserId

interface UserRepository {
    fun create(platformId: String, platform: AuthPlatform, name: String, intro: String): User?
    fun getUser(platformId: String, platform: AuthPlatform): User?
    fun withdraw(userId: UserId)
}
