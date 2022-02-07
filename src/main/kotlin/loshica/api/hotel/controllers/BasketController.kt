package loshica.api.hotel.controllers

import loshica.api.hotel.errors.ApiError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.models.Basket
import loshica.api.hotel.models.User
import loshica.api.hotel.services.BasketService
import loshica.api.hotel.services.UserService
import loshica.api.hotel.shared.Auth
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.basket)
class BasketController(
    private val basketService: BasketService,
    private val userService: UserService
) {

    @GetMapping
    fun getAll(
        @RequestHeader authorization: String?
    ): Iterable<Basket> {
        Auth.checkRoles(authorization)
        return basketService.getAll()
    }

    @GetMapping(Route.current)
    fun getOne(@RequestParam _user: String?): Basket {
        if (_user == null) throw ApiError(ErrorMessage.badRequest)
        val user: User = userService.getOne(_user.toInt())
        return basketService.getByUser(user)
    }
}