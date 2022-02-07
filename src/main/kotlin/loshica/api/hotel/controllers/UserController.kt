package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.UserDto
import loshica.api.hotel.errors.ApiError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.models.Basket
import loshica.api.hotel.models.Order
import loshica.api.hotel.shared.UserData
import loshica.api.hotel.models.User
import loshica.api.hotel.responses.UserResponse
import loshica.api.hotel.services.*
import loshica.api.hotel.shared.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.user)
class UserController(
    private val userService: UserService,
    private val basketService: BasketService,
    private val orderService: OrderService,
    private val roomService: RoomService
) {

    @GetMapping
    fun getAll(@RequestHeader authorization: String?): Iterable<User> {
        Auth.checkRoles(authorization)
        return userService.getAll()
    }

    @PostMapping(Route.register)
    @ResponseBody
    fun register(@RequestBody dto: UserDto): UserResponse {
        if (dto.email.isBlank() || dto.password.isBlank()) {
            throw ApiError(ErrorMessage.badRequest)
        }

        if (dto.email.split(Regex("[@.]")).size < Constants.minEmailChunks) {
            println(dto.email.split(Regex("[@.]")))
            throw ApiError(ErrorMessage.invalidEmail)
        }

        if (dto.password.length < Constants.minPasswordLength) {
            throw ApiError(ErrorMessage.shortPassword)
        }

        val candidate: User? = userService.getByEmail(dto.email)
        candidate?.let { throw ApiError(ErrorMessage.userExists) }
        val usersAmount: Int = userService.countAmount()
        val hashedPassword: String = Bcrypt.hashPassword(dto.password)

        val user: User = userService.create(
            email = dto.email,
            password = hashedPassword,
            role = if (usersAmount == 0) Role.admin else Role.client
        )
        basketService.create(user)

        return UserResponse(
            token = Jwt.generateToken(user),
            id = user.id.toString()
        )
    }

    @PostMapping(Route.login)
    @ResponseBody
    fun login(@RequestBody dto: UserDto): UserResponse {
        val user: User = userService
            .getByEmail(dto.email)
            ?: throw ApiError(ErrorMessage.noUser)

        if (!user.comparePasswords(dto.password)) {
            throw ApiError(ErrorMessage.invalidPassword)
        }

        return UserResponse(
            token = Jwt.generateToken(user),
            id = user.id.toString()
        )
    }

    @PostMapping(Route.auth)
    @ResponseBody
    fun auth(@RequestHeader authorization: String?): UserResponse {
        val userData: UserData = Auth.getData(authorization)

        val user: User = userService
            .getByEmail(userData.email)
            ?: throw ApiError(ErrorMessage.noUser)

        return UserResponse(
            token = Jwt.generateToken(user),
            id = user.id.toString()
        )
    }

    @PatchMapping
    @ResponseBody
    fun change(
        @RequestBody dto: UserDto,
        @RequestHeader authorization: String?
    ): User {
        Auth.checkRoles(authorization)
        return userService.change(
            id = dto._id.toInt(),
            role = dto.role
        )
    }

    @DeleteMapping
    fun delete(
        @RequestBody dto: UserDto,
        @RequestHeader authorization: String?
    ): String {
        Auth.checkRoles(authorization)

        val user: User = userService.getOne(dto._id.toInt())
        val basket: Basket = basketService.getByUser(user)
        val orders: Iterable<Order> = orderService.get(basket)

        orders.forEach {
            roomService.unBookRoom(it.room.id)
            basketService.removeOrder(it)
            orderService.delete(it.id)
        }

        basketService.delete(basket.id)

        return userService
            .delete(id = dto._id.toInt())
            .toString()
    }
}