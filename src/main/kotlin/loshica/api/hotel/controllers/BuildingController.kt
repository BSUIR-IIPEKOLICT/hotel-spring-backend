package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.BuildingDto
import loshica.api.hotel.models.Building
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.building)
class BuildingController(
    private val buildingService: IBuildingService,
    private val basketService: IBasketService,
    private val orderService: IOrderService,
    private val roomService: IRoomService,
    private val reviewService: IReviewService
) {

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
    fun delete(@RequestBody dto: BuildingDto): String {
        val building: Building = buildingService.getOne(dto._id.toInt())

        roomService.getByBuilding(building).forEach {
            it.orderField?.let { order ->
                basketService.removeOrder(order)
                orderService.delete(order.id)
            }

            reviewService.deleteWithRoom(it)
            buildingService.removeRoom(it)
            roomService.delete(it.id)
        }

        return buildingService
            .delete(id = dto._id.toInt())
            .toString()
    }
}