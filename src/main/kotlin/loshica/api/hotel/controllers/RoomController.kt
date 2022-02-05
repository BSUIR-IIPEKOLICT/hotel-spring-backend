package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.models.Room
import loshica.api.hotel.services.RoomService
import loshica.api.hotel.shared.Constants
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.room)
class RoomController(private val roomService: RoomService) {

    @GetMapping
    fun get(
        @RequestParam _building: String?,
        @RequestParam _type: String?,
        @RequestParam isFree: Boolean?,
        @RequestParam page: Int?,
        @RequestParam limit: Int?
    ): Iterable<Room> {
        val requestPage: Int = page ?: 1
        val requestLimit: Int = limit ?: Constants.roomLimit
        val offset: Int = requestPage * requestLimit - requestLimit

        return roomService.get(
            buildingId = _building,
            typeId = _type,
            isFree = isFree,
            limit = requestLimit,
            offset = offset
        )
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: RoomDto): Room = roomService.create(
        dto._building.toInt(),
        dto._type.toInt()
    )

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: RoomDto): Room = roomService.change(
        id = dto._id.toInt(),
        buildingId = dto._building.toInt(),
        typeId = dto._type.toInt()
    )

    @DeleteMapping
    fun delete(@RequestBody dto: RoomDto): String {
        return roomService.delete(dto._id.toInt()).toString()
    }
}