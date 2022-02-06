package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.OrderDto
import loshica.api.hotel.models.*
import loshica.api.hotel.services.*
import loshica.api.hotel.shared.BaseOrderDto
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.order)
class OrderController(
    private val orderService: OrderService,
    private val roomService: RoomService,
    private val serviceService: ServiceService
) {

    @GetMapping
    fun get(@RequestParam _basket: String): Iterable<Order> {
        return orderService.get(basket = _basket.toInt())
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: OrderDto): BaseOrderDto {
        val room: Room = roomService.getOne(dto._room.toInt())
        val services: Iterable<Service> = serviceService.getByIds(dto._services)

        return orderService.create(
            basket = dto._basket.toInt(),
            room = room,
            services = services.toMutableList(),
            duty = dto.duty,
            population = dto.population,
            date = dto.date
        )
    }

    @DeleteMapping
    fun delete(@RequestBody dto: OrderDto): String {
        val order: Order = orderService.getOne(dto._id.toInt())
        val room: Room = roomService.getOne(order.room.id)

        room.unBook()
        return orderService.delete(id = dto._id.toInt()).toString()
    }
}