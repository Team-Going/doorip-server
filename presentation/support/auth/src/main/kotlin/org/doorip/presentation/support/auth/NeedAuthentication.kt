package org.doorip.presentation.support.auth

import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("isAuthenticated()")
annotation class NeedAuthentication
