package org.doorip.core.auth

import org.doorip.domain.entity.Token
import org.doorip.domain.entity.UserId
import org.doorip.domain.entity.UserInfo

interface AuthUseCase {
    fun signIn(token: String, platform: String): UserInfo
    fun signUp(token: String, platform: String, name: String, intro: String): Token
    fun signOut(userId: UserId)
    fun reissue(refreshToken: String): Token
    fun withdraw(userId: UserId)
}
