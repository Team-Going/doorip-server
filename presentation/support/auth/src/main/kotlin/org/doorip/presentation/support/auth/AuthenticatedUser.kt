package org.doorip.presentation.support.auth

import org.doorip.domain.entity.UserId

data class AuthenticatedUser(
    val userId: UserId,
)
