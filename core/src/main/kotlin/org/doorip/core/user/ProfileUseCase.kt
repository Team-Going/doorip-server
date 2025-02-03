package org.doorip.core.user

import org.doorip.domain.user.User
import org.doorip.domain.user.UserId

interface ProfileUseCase {
    fun getProfile(userId: UserId): User
    fun updateProfile(userId: UserId, name: String, intro: String)
}
