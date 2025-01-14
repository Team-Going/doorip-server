package org.doorip.presentation.support.auth

import org.doorip.domain.user.UserId

data class AuthenticatedUser(
    val userId: UserId,
)
