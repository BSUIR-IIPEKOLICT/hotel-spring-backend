package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.BuildingDto
import loshica.api.hotel.models.Building
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.models.User
import loshica.api.hotel.shared.Role
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.BUILDINGS)
class BuildingController(private val buildingService: IBuildingService) {

    @GetMapping
    fun getAll(): List<BuildingDto> = buildingService.getAll().map { it.toDto() }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): BuildingDto = buildingService.getOne(id).toDto()

    @PostMapping
    fun create(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: BuildingDto
    ): BuildingDto {
        return buildingService.create(address = dto.address).toDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: BuildingDto,
        @PathVariable id: Int
    ): BuildingDto {
        return buildingService.change(id = id, address = dto.address).toDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        val building: Building = buildingService.getOne(id)
        building.rooms.forEach { MainController.deleteRoom(it) }
        return DeleteDto(id = buildingService.delete(id))
    }
}