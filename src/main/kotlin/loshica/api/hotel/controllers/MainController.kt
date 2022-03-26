package loshica.api.hotel.controllers

import loshica.api.hotel.interfaces.*
import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.Option
import loshica.api.hotel.models.Room
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class MainController {

    @GetMapping
    fun healthCheck(): Map<String, String> = mapOf("status" to "ok")

    companion object {
        private lateinit var buildingService: IBuildingService
        private lateinit var typeService: ITypeService
        private lateinit var roomService: IRoomService
        private lateinit var commentService: ICommentService
        private lateinit var bookingService: IBookingService
        private lateinit var userService: IUserService
        private lateinit var optionService: IOptionService

        private fun isolateBooking(booking: Booking) {
            roomService.unBookRoom(booking)
            userService.removeBooking(booking)
        }

        fun deleteRoom(room: Room): Int {
            room.bookings.forEach { deleteBooking(it) }
            room.comments.forEach { roomService.removeComment(it) }

            buildingService.removeRoom(room)
            typeService.removeRoom(room)
            commentService.deleteWithRoom(room)
            return roomService.delete(room.id)
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
            return optionService.delete(option.id)
        }
    }
}