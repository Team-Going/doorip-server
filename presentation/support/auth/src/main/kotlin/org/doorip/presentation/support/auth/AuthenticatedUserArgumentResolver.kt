package org.doorip.presentation.support.auth

import org.doorip.domain.entity.UserId
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
internal class AuthenticatedUserArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return AuthenticatedUser::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?,
    ): AuthenticatedUser? {
        val principal = SecurityContextHolder.getContext()
            ?.authentication
            ?.principal as? UserId
            ?: return null
        return AuthenticatedUser(principal)
    }
}
