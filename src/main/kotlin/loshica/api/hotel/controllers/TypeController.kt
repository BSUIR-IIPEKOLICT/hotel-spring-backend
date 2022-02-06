package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.models.Type
import loshica.api.hotel.services.ServiceService
import loshica.api.hotel.services.TypeService
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.type)
class TypeController(
    private val typeService: TypeService,
    private val serviceService: ServiceService
) {

    @GetMapping
    fun getAll(): Iterable<Type> = typeService.getAll()

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: TypeDto): Type = typeService.create(
        name = dto.name,
        places = dto.places,
        services = serviceService.getByIds(dto._services).toMutableList()
    )

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: TypeDto): Type {
        val type: Type = typeService.getOne(dto._id.toInt())
        type.change(
            name = dto.name,
            places = dto.places,
            services = serviceService.getByIds(dto._services).toMutableList()
        )
        return type
    }

    @DeleteMapping
    fun delete(@RequestBody dto: TypeDto): String = typeService
        .delete(id = dto._id.toInt())
        .toString()
}