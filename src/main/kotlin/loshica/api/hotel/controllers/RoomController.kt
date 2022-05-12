package loshica.api.hotel.controllers

import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.dtos.RoomPaginationDto
import loshica.api.hotel.dtos.RoomPopulatedDto
import loshica.api.hotel.models.*
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.shared.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.ROOMS)
@CrossOrigin(originPatterns = ["*"])
class RoomController(
    bookingService: IBookingService,
    private val buildingService: IBuildingService,
    commentService: ICommentService,
    optionService: IOptionService,
    private val roomService: IRoomService,
    private val typeService: ITypeService,
    userService: IUserService
) {

    private val destroyer = Destroyer(
        bookingService = bookingService,
        buildingService = buildingService,
        commentService = commentService,
        optionService = optionService,
        roomService = roomService,
        typeService = typeService,
        userService = userService
    )

    @GetMapping
    fun getAll(): List<RoomPopulatedDto> = roomService.getAll().map { it.toPopulatedDto() }

    @GetMapping(Route.PAGINATED)
    fun getWithPagination(
        @RequestParam buildingId: Int?,
        @RequestParam typeId: Int?,
        @RequestParam isFree: Boolean?,
        @RequestParam page: Int?,
        @RequestParam limit: Int?
    ): RoomPaginationDto {
        val requestPage: Int = page ?: 1
        val requestLimit: Int = limit ?: Configuration.ROOM_LIMIT
        val offset: Int = requestPage * requestLimit - requestLimit

        return RoomPaginationDto(
            amount = roomService.getAmount(
                buildingId = buildingId,
                typeId = typeId,
                isFree = isFree
            ),
            rooms = roomService
                .get(
                    buildingId = buildingId,
                    typeId = typeId,
                    isFree = isFree,
                    limit = requestLimit,
                    offset = offset
                )
                .toList().map { it.toPopulatedDto() }
        )
    }

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): RoomPopulatedDto = roomService.getOne(id).toPopulatedDto()

    @PostMapping
    fun create(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: RoomDto
    ): RoomPopulatedDto {
        val room: Room = roomService.create(
            building = buildingService.getOne(dto.building),
            type = typeService.getOne(dto.type)
        )

        buildingService.addRoom(room)
        return room.toPopulatedDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @Auth(Role.ADMIN) user: User,
        @RequestBody dto: RoomDto,
        @PathVariable id: Int
    ): RoomPopulatedDto {
        var room: Room = roomService.getOne(id)

        buildingService.removeRoom(room)
        room = roomService.change(
            id = id,
            building = buildingService.getOne(dto.building),
            type = typeService.getOne(dto.type)
        )
        buildingService.addRoom(room)

        return room.toPopulatedDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth(Role.ADMIN) user: User,
        @PathVariable id: Int
    ): DeleteDto {
        val room: Room = roomService.getOne(id)
        return DeleteDto(id = destroyer.deleteRoom(room))
    }
}