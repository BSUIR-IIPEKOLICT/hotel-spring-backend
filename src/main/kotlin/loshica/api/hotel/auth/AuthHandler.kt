package loshica.api.hotel.auth

import loshica.api.hotel.dtos.TokenDto
import loshica.api.hotel.errors.EntityNotFoundError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.errors.UnauthorizedError
import loshica.api.hotel.interfaces.IUserService
import loshica.api.hotel.models.User
import loshica.api.hotel.shared.Jwt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.context.request.NativeWebRequest

@Component
class AuthHandler {
    @Autowired private lateinit var userService: IUserService

    fun getUser(request: NativeWebRequest, roles: Array<out String>): User {
        try {
            val authToken: String = request.getHeader(HttpHeaders.AUTHORIZATION)
                ?.split(" ")
                ?.get(1)
                ?: throw UnauthorizedError()

            val tokenData: TokenDto = Jwt.decode(authToken)

            if (roles.isNotEmpty() && roles.indexOf(tokenData.role) == -1) {
                throw UnauthorizedError(ErrorMessage.NO_ACCESS)
            }

            val user: User = userService.getOne(tokenData.id)

            if (!user.isActive) {
                throw EntityNotFoundError(User.Companion::class.java.name, tokenData.id)
            }

            return user
        } catch (e: Exception) {
            throw UnauthorizedError()
        }
    }
}