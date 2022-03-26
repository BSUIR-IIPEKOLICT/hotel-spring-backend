package loshica.api.hotel.auth

import loshica.api.hotel.annotations.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthResolver : HandlerMethodArgumentResolver {
    @Autowired private lateinit var authHandler: AuthHandler

    override fun supportsParameter(
        methodParameter: MethodParameter
    ) : Boolean {
        return methodParameter.getParameterAnnotation(Auth::class.java) != null
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authAnnotation: Auth? = parameter.getParameterAnnotation(Auth::class.java)

        authAnnotation?.let {
            return authHandler.getUser(webRequest, it.roles)
        }

        return null
    }
}