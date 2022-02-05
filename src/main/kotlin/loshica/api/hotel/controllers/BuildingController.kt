package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.BuildingDto
import loshica.api.hotel.models.Building
import loshica.api.hotel.services.BuildingService
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.building)
class BuildingController(private val buildingService: BuildingService) {

    @GetMapping
    fun getAll(): Iterable<Building> = buildingService.getAll()

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: BuildingDto): Building = buildingService.create(
        address = dto.address
    )

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: BuildingDto): Building = buildingService.change(
        id = dto._id.toInt(),
        address = dto.address
    )

    @DeleteMapping
    fun delete(@RequestBody dto: BuildingDto): String = buildingService
        .delete(id = dto._id.toInt())
        .toString()
}