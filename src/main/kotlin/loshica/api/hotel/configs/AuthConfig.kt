package loshica.api.hotel.configs

import loshica.api.hotel.auth.AuthResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class AuthConfig : WebMvcConfigurationSupport() {
    @Bean
    fun authResolverFactory() : HandlerMethodArgumentResolver {
        return AuthResolver()
    }

    override fun addArgumentResolvers(
        argumentResolvers: MutableList<HandlerMethodArgumentResolver>
    ) {
        argumentResolvers.add(authResolverFactory())
    }
}