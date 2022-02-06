package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.models.*
import loshica.api.hotel.responses.RoomsWithAmountResponse
import loshica.api.hotel.services.*
import loshica.api.hotel.shared.Constants
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.room)
class RoomController(
    private val roomService: RoomService,
    private val buildingService: BuildingService,
    private val typeService: TypeService,
    private val reviewService: ReviewService,
    private val orderService: OrderService,
    private val basketService: BasketService
) {

    @GetMapping
    fun get(
        @RequestParam _building: String?,
        @RequestParam _type: String?,
        @RequestParam isFree: Boolean?,
        @RequestParam page: Int?,
        @RequestParam limit: Int?
    ): RoomsWithAmountResponse {
        val requestPage: Int = page ?: 1
        val requestLimit: Int = limit ?: Constants.roomLimit
        val offset: Int = requestPage * requestLimit - requestLimit

        return RoomsWithAmountResponse(
            rooms = roomService.get(
                buildingId = _building,
                typeId = _type,
                isFree = isFree,
                limit = requestLimit,
                offset = offset
            ),
            amount = roomService.getAmount(
                buildingId = _building,
                typeId = _type,
                isFree = isFree
            )
        )
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: RoomDto): Room {
        val building: Building = buildingService.getOne(dto._building.toInt())
        val type: Type = typeService.getOne(dto._type.toInt())
        val room: Room = roomService.create(building = building, type = type)

        buildingService.addRoom(room)
        return room
    }

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: RoomDto): Room {
        val building: Building = buildingService.getOne(dto._building.toInt())
        val type: Type = typeService.getOne(dto._type.toInt())
        var room: Room = roomService.getOne(dto._id.toInt())

        buildingService.removeRoom(room)
        room = roomService.change(
            id = dto._id.toInt(),
            building = building,
            type = type
        )
        buildingService.addRoom(room)

        return room
    }

    @DeleteMapping
    fun delete(@RequestBody dto: RoomDto): String {
        val room: Room = roomService.getOne(dto._id.toInt())
        buildingService.removeRoom(room)
        reviewService.deleteWithRoom(room)

        room.orderField?.let {
            basketService.removeOrder(it)
            orderService.delete(it.id)
        }

        return roomService
            .delete(id = dto._id.toInt())
            .toString()
    }
}