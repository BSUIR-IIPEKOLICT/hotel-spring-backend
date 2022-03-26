package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*
import java.util.*

interface IBookingService : IBaseService<Booking> {
    fun getByUser(user: User): Iterable<Booking>

    fun create(
        user: User,
        room: Room,
        options: List<Option>,
        price: Int,
        population: Int,
        startDate: Date?,
        endDate: Date?
    ): Booking

    fun removeOption(option: Option)
    fun disable(booking: Booking)
    fun deleteWithRoom(room: Room)
}