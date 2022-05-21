package loshica.api.hotel.controllers

import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.dtos.OptionDto
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.models.Option
import loshica.api.hotel.models.User
import loshica.api.hotel.shared.Destroyer
import loshica.api.hotel.shared.Role
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.OPTIONS)
@CrossOrigin(originPatterns = ["*"])
class OptionController(
    bookingService: IBookingService,
    buildingService: IBuildingService,
    commentService: ICommentService,
    private val optionService: IOptionService,
    roomService: IRoomService,
    typeService: ITypeService,
    userService: IUserService
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
    fun getAll(): List<OptionDto> = optionService.getAll()
        .filter { it.isActive }
        .map { it.toDto() }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): OptionDto = optionService.getOne(id).toDto()

    @PostMapping
    fun create(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: OptionDto
    ): OptionDto {
        return optionService.create(name = dto.name, price = dto.price).toDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: OptionDto,
        @PathVariable id: Int
    ): OptionDto {
        return optionService.change(id = id, name = dto.name, price = dto.price).toDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        val option: Option = optionService.getOne(id)
        return DeleteDto(id = destroyer.deleteOption(option))
    }
}