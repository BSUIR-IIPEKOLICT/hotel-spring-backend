package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.models.Type
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.TYPES)
@CrossOrigin(originPatterns = ["*"])
class TypeController(
    private val commentService: ICommentService,
    private val roomService: IRoomService,
    private val typeService: ITypeService
) {

    @GetMapping
    fun getAll(): List<TypeDto> = typeService.getAll().map { it.toDto() }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): TypeDto = typeService.getOne(id).toDto()

    @PostMapping
    fun create(@RequestBody dto: TypeDto): TypeDto = typeService.create(dto).toDto()

    @PutMapping(Selector.ID)
    fun change(
        @RequestBody dto: TypeDto,
        @PathVariable id: Int
    ): TypeDto {
        return typeService.change(id = id, dto = dto).toDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(@PathVariable id: Int): DeleteDto {
        val type: Type = typeService.getOne(id)

        type.rooms.forEach {
            typeService.removeRoom(it)
            commentService.deleteWithRoom(it)
            roomService.delete(it.id)
        }

        return DeleteDto(id = typeService.delete(id))
    }
}