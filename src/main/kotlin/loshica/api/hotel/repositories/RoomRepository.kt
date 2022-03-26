package loshica.api.hotel.repositories

import loshica.api.hotel.models.Room
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : CrudRepository<Room, Int>