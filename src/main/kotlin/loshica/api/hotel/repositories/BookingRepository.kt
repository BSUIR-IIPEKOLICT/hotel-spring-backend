package loshica.api.hotel.repositories

import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository : CrudRepository<Booking, Int> {
    fun findByCreatedBy(createdBy: User): Iterable<Booking>
}