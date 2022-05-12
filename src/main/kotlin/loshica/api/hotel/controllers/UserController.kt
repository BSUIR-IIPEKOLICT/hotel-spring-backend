package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.UserDto
import loshica.api.hotel.models.User
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.shared.*
import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.UserPopulatedDto
import loshica.api.hotel.interfaces.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.USERS)
@CrossOrigin(originPatterns = ["*"])
class UserController(
    bookingService: IBookingService,
    buildingService: IBuildingService,
    commentService: ICommentService,
    optionService: IOptionService,
    roomService: IRoomService,
    typeService: ITypeService,
    private val userService: IUserService
) {

    private val destroyer = Destroyer(
        bookingService = bookingService,
        buildingService = buildingService,
        commentService = commentService,
        optionService = optionService,
        roomService = roomService,
        typeService = typeService,
        userService = userService
    )

    @GetMapping
    fun getAll(): List<UserPopulatedDto> {
        return userService.getAll().map { it.toPopulatedDto() }
    }

    @GetMapping(Selector.ID)
    fun getOne(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): UserPopulatedDto {
        return userService.getOne(id).toPopulatedDto()
    }

    @PatchMapping("${Selector.ID}/${Route.CREDENTIALS}")
    fun changeCredentials(
        @Auth user: User,
        @RequestBody dto: UserDto,
        @PathVariable id: Int
    ): UserPopulatedDto {
        return userService
            .change(
                id = id,
                email = dto.email,
                password = dto.password,
                role = user.role
            )
            .toPopulatedDto()
    }

    @PatchMapping("${Selector.ID}/${Route.ROLE}")
    fun changeRole(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: UserDto,
        @PathVariable id: Int
    ): UserPopulatedDto {
        return userService
            .change(
                id = id,
                email = user.email,
                password = user.password,
                role = dto.role
            )
            .toPopulatedDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        user.bookings.forEach { destroyer.deleteBooking(it) }
        return DeleteDto(id = userService.delete(id))
    }
}