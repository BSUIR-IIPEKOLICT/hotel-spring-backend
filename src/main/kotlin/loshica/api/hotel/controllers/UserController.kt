package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.UserDto
import loshica.api.hotel.interfaces.IUserService
import loshica.api.hotel.models.User
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.shared.*
import loshica.api.hotel.annotations.Auth
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.USERS)
class UserController(private val userService: IUserService) {

    @GetMapping
    fun getAll(@Auth(Role.ADMIN) user: User): List<UserDto> {
        return userService.getAll().map { it.toDto() }
    }

    @GetMapping(Selector.ID)
    fun getOne(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): UserDto {
        return userService.getOne(id).toDto()
    }

    @PatchMapping("${Selector.ID}/${Route.CREDENTIALS}")
    fun changeCredentials(
        @Auth user: User,
        @RequestBody dto: UserDto,
        @PathVariable id: Int
    ): UserDto {
        return userService.change(
            id = id,
            email = dto.email,
            password = dto.password,
            role = user.role
        ).toDto()
    }

    @PatchMapping("${Selector.ID}/${Route.ROLE}")
    fun changeRole(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: UserDto,
        @PathVariable id: Int
    ): UserDto {
        return userService.change(
            id = id,
            email = user.email,
            password = user.password,
            role = dto.role
        ).toDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        user.bookings.forEach { MainController.deleteBooking(it) }
        return DeleteDto(id = userService.delete(id))
    }
}