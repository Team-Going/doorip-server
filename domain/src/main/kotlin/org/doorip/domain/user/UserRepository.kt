package org.doorip.domain.user

import org.doorip.domain.auth.AuthPlatform

interface UserRepository {
    fun create(platformId: String, platform: AuthPlatform, name: String, intro: String): User?
    fun getUser(platformId: String, platform: AuthPlatform): User?
    fun getUser(userId: UserId): User?
    fun updateUser(userId: UserId, name: String, intro: String)
    fun withdraw(userId: UserId)
}
