package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.UserDto
import loshica.api.hotel.errors.BadRequestError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.interfaces.IUserService
import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.models.User
import loshica.api.hotel.dtos.AuthDto
import loshica.api.hotel.shared.Jwt
import loshica.api.hotel.shared.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.AUTH)
@CrossOrigin(originPatterns = ["*"])
class AuthController (private val userService: IUserService) {

    private fun getUserByEmail(email: String): User = userService.getByEmail(email)
        ?: throw BadRequestError(ErrorMessage.NO_USER_WITH_THIS_EMAIL)

    @GetMapping
    fun auth(@Auth user: User): AuthDto {
        return AuthDto(token = Jwt.generateToken(user))
    }

    @PostMapping(Route.REGISTER)
    fun register(@RequestBody dto: UserDto): AuthDto {
        if (dto.email.isBlank() || dto.password.isBlank()) {
            throw BadRequestError()
        }

        if (dto.email.split(Regex("[@.]")).size < Configuration.MIN_EMAIL_CHUNKS) {
            throw BadRequestError(ErrorMessage.INVALID_EMAIL)
        }

        if (dto.password.length < Configuration.MIN_PASSWORD_LENGTH) {
            throw BadRequestError(ErrorMessage.BAD_PASSWORD)
        }

        val candidate: User? = userService.getByEmail(dto.email)

        candidate?.let { throw BadRequestError(ErrorMessage.USER_ALREADY_EXISTS) }

        val usersAmount: Int = userService.getAmount()

        val user: User = userService.create(
            email = dto.email,
            password = dto.password,
            role = if (usersAmount == 0) Role.ADMIN else Role.CLIENT
        )

        return AuthDto(token = Jwt.generateToken(user))
    }

    @PostMapping(Route.LOGIN)
    fun login(@RequestBody dto: UserDto): AuthDto {
        val user: User = getUserByEmail(dto.email)

        if (!user.comparePasswords(dto.password)) {
            throw BadRequestError(ErrorMessage.INVALID_PASSWORD)
        }

        return AuthDto(token = Jwt.generateToken(user))
    }
}