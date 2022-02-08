package loshica.api.hotel.repositories

import loshica.api.hotel.models.Building
import loshica.api.hotel.models.Room
import loshica.api.hotel.models.Type
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : CrudRepository<Room, Int> {
    fun findByBuilding(building: Building): Iterable<Room>
    fun findByType(type: Type): Iterable<Room>
}