package loshica.api.hotel.controllers

import loshica.api.hotel.annotations.Auth
import loshica.api.hotel.dtos.BookingDto
import loshica.api.hotel.dtos.BookingPopulatedDto
import loshica.api.hotel.dtos.BookingResponseDto
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.models.*
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import loshica.api.hotel.shared.Destroyer
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.BOOKINGS)
@CrossOrigin(originPatterns = ["*"])
class BookingController(
    private val bookingService: IBookingService,
    buildingService: IBuildingService,
    commentService: ICommentService,
    private val optionService: IOptionService,
    private val roomService: IRoomService,
    typeService: ITypeService,
    private val userService: IUserService
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
    fun get(@RequestParam userId: Int): BookingResponseDto {
        val user: User = userService.getOne(userId)

        return BookingResponseDto(
            active = user.bookings.filter { it.isActive }.map { it.toPopulatedDto() },
            inActive = user.bookings.filter { !it.isActive }.map { it.toPopulatedDto() }
        )
    }

    @GetMapping(Selector.ID)
    fun getOne(
        @Auth user: User,
        @PathVariable id: Int
    ): BookingPopulatedDto {
        return bookingService.getOne(id).toPopulatedDto()
    }

    @PostMapping
    fun create(
        @Auth user: User,
        @RequestBody dto: BookingDto
    ): BookingPopulatedDto {
        val booking: Booking = bookingService.create(
            user = user,
            room = roomService.getOne(dto.room),
            options = optionService.getByIds(dto.options),
            price = dto.price,
            population = dto.population,
            startDate = dto.startDate,
            endDate = dto.endDate
        )

        userService.addBooking(booking)
        roomService.bookRoom(booking)

        return booking.toPopulatedDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(
        @Auth user: User,
        @PathVariable id: Int
    ): DeleteDto {
        val booking: Booking = bookingService.getOne(id)
        destroyer.disableBooking(booking)
        return DeleteDto(id = id)
    }
}