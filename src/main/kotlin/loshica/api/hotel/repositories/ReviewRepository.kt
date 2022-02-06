package loshica.api.hotel.repositories

import loshica.api.hotel.models.Review
import loshica.api.hotel.models.Room
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : CrudRepository<Review, Int> {
    fun findByRoom(room: Room): Iterable<Review>
}