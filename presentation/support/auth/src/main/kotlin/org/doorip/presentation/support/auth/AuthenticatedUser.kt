package org.doorip.presentation.support.auth

import org.doorip.domain.UserId

data class AuthenticatedUser(
    val userId: UserId,
)
