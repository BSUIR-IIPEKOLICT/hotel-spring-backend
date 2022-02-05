package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.ServiceDto
import loshica.api.hotel.models.Service
import loshica.api.hotel.services.ServiceService
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.service)
class ServiceController(private val serviceService: ServiceService) {

    @GetMapping
    fun getAll(): Iterable<Service> = serviceService.getAll()

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: ServiceDto): Service = serviceService.create(
        name = dto.name,
        price = dto.price
    )

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: ServiceDto): Service = serviceService.change(
        id = dto._id.toInt(),
        name = dto.name,
        price = dto.price
    )

    @DeleteMapping
    fun delete(@RequestBody dto: ServiceDto): String = serviceService
        .delete(id = dto._id.toInt())
        .toString()
}