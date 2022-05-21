package loshica.api.hotel.shared

import loshica.api.hotel.interfaces.*
import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.Option
import loshica.api.hotel.models.Room

class Destroyer(
    private val bookingService: IBookingService,
    private val buildingService: IBuildingService,
    private val commentService: ICommentService,
    private val optionService: IOptionService,
    private val roomService: IRoomService,
    private val typeService: ITypeService,
    private val userService: IUserService
) {

    private fun isolateBooking(booking: Booking) {
        roomService.unBookRoom(booking)
        userService.removeBooking(booking)
    }

    fun disableBooking(booking: Booking) {
        isolateBooking(booking)
        bookingService.disable(booking)
    }

    fun deleteBooking(booking: Booking): Int {
        isolateBooking(booking)
        return bookingService.delete(booking.id)
    }

    fun deleteOption(option: Option): Int {
        typeService.removeOption(option)
        bookingService.removeOption(option)
        return optionService.disable(option.id)
    }

    fun deleteRoom(room: Room): Int {
        room.bookings.forEach { deleteBooking(it) }
        room.comments.forEach { roomService.removeComment(it) }

        buildingService.removeRoom(room)
        typeService.removeRoom(room)
        commentService.deleteWithRoom(room)
        return roomService.delete(room.id)
    }
}