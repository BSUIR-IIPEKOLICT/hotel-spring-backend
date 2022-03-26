package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IBookingService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.BookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingService(
    @Autowired override val repository: BookingRepository
) : BaseService<Booking, BookingRepository>(repository), IBookingService {

    override fun getByUser(user: User): Iterable<Booking> = repository.findByCreatedBy(user)

    override fun create(
        user: User,
        room: Room,
        options: List<Option>,
        price: Int,
        population: Int,
        startDate: Date?,
        endDate: Date?
    ): Booking {
        val booking = Booking(
            createdBy = user,
            room = room,
            options = options.toMutableList(),
            price = price,
            population = population,
            startDate = startDate ?: Date(),
            endDate = endDate
        )
        repository.save(booking)
        return booking
    }

    override fun removeOption(option: Option) = getAll().forEach { it.options.remove(option) }

    override fun disable(booking: Booking) {
        booking.disable()
        repository.save(booking)
    }

    override fun deleteWithRoom(room: Room) {
        repository.deleteAll(room.bookings)
    }
}