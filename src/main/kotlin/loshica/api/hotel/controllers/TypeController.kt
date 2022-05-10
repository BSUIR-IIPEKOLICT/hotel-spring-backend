package loshica.api.hotel.controllers

import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.dtos.TypePopulatedDto
import loshica.api.hotel.models.Type
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.models.User
import loshica.api.hotel.shared.Role
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.TYPES)
@CrossOrigin(originPatterns = ["*"])
class TypeController(
    private val typeService: ITypeService,
    private val optionService: IOptionService
) {

    @GetMapping
    fun getAll(): List<TypePopulatedDto> = typeService.getAll().map { it.toPopulatedDto() }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): TypePopulatedDto = typeService.getOne(id).toPopulatedDto()

    @PostMapping
    fun create(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: TypeDto
    ): TypePopulatedDto {
        return typeService.create(
            name = dto.name,
            places = dto.places,
            price = dto.price,
            options = optionService.getByIds(dto.options)
        ).toPopulatedDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: TypeDto,
        @PathVariable id: Int
    ): TypePopulatedDto {
        return typeService.change(
            id = id,
            name = dto.name,
            places = dto.places,
            price = dto.price,
            options = optionService.getByIds(dto.options)
        ).toPopulatedDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        val type: Type = typeService.getOne(id)
        type.rooms.forEach { MainController.deleteRoom(it) }
        return DeleteDto(id = typeService.delete(id))
    }
}