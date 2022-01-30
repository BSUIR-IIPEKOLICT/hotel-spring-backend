package loshica.api.hotel.repositories

import loshica.api.hotel.models.Review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : CrudRepository<Review, Int> {
    fun findByRoomId(roomId: Int): Iterable<Review>
    fun deleteByRoomId(roomId: Int)
}