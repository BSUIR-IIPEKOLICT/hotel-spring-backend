package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.CommentDto
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.dtos.RoomPopulatedDto
import loshica.api.hotel.models.*
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.shared.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.ROOMS)
@CrossOrigin(originPatterns = ["*"])
class RoomController(
    private val commentService: ICommentService,
    private val roomService: IRoomService,
    private val typeService: ITypeService,
) {

    @GetMapping
    fun getAll(): List<RoomPopulatedDto> = roomService.getAll().map { it.toPopulatedDto() }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): RoomPopulatedDto = roomService.getOne(id).toPopulatedDto()

    @GetMapping("${Selector.ROOM_ID}/comments")
    fun getRoomComments(@PathVariable roomId: Int): List<CommentDto> {
        return roomService.getOne(roomId).comments.map { it.toDto() }
    }

    @PostMapping
    fun create(@RequestBody dto: RoomDto): RoomPopulatedDto {
        val room: Room = roomService.create(
            type = typeService.getOne(dto.type),
            dto = dto
        )

        typeService.addRoom(room)
        return room.toPopulatedDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @RequestBody dto: RoomDto,
        @RequestParam status: String?,
        @PathVariable id: Int
    ): RoomPopulatedDto {
        typeService.removeRoom(roomService.getOne(id))

        val room: Room = roomService.change(
            id = id,
            type = typeService.getOne(dto.type),
            isFree = status != "booked",
            dto = dto
        )

        typeService.addRoom(room)
        return room.toPopulatedDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(@PathVariable id: Int): DeleteDto {
        val room: Room = roomService.getOne(id)

        typeService.removeRoom(room)
        commentService.deleteWithRoom(room)

        return DeleteDto(id = roomService.delete(room.id))
    }
}
