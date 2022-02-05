package loshica.api.hotel.services

import loshica.api.hotel.models.Review
import loshica.api.hotel.models.Room
import loshica.api.hotel.repositories.RoomRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoomService(@Autowired private val roomRepository: RoomRepository) {

    private fun getByQuery(buildingId: String?, typeId: String?, isFree: Boolean?): Iterable<Room> {
        var rooms: Iterable<Room> = roomRepository.findAll()

        if (buildingId != null) {
            rooms = rooms.filter { room: Room -> room.building == buildingId.toInt() }
        }

        if (typeId != null) {
            rooms = rooms.filter { room: Room -> room.type == typeId.toInt() }
        }

        if (isFree != null) {
            rooms = rooms.filter { room: Room -> room.isFree == isFree }
        }

        return rooms
    }

    fun get(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?,
        limit: Int = Int.MAX_VALUE,
        offset: Int = 0
    ): Iterable<Room> {
        val rooms: Iterable<Room> = this.getByQuery(buildingId, typeId, isFree)

        return rooms
            .withIndex()
            .filter { indexedValue: IndexedValue<Room> ->
                indexedValue.index >= offset && indexedValue.index <= offset + limit
            }
            .map { indexedValue: IndexedValue<Room> -> indexedValue.value }
    }

    fun getOne(id: Int): Room = roomRepository.findByIdOrNull(id) ?: throw Exception(Constants.notFoundMessage)

    fun getAmount(buildingId: String?, typeId: String?, isFree: Boolean?): Int =
        this.getByQuery(buildingId, typeId, isFree).count()

    fun create(buildingId: Int, typeId: Int): Room {
        val room = Room(building = buildingId, type = typeId)
        roomRepository.save(room)
        return room
    }

    fun change(id: Int, buildingId: Int, typeId: Int): Room {
        val room: Room = this.getOne(id)
        room.building = buildingId
        room.type = typeId
        roomRepository.save(room)
        return room
    }

    fun addReview(id: Int, review: Review) {
        val room: Room = this.getOne(id)
        room.reviews.add(review)
        roomRepository.save(room)
    }

    fun removeReview(id: Int, review: Review) {
        val room: Room = this.getOne(id)
        room.reviews.remove(review)
        roomRepository.save(room)
    }

    fun bookRoom(id: Int, orderId: Int, population: Int) {
        val room: Room = this.getOne(id)
        room.orderField = orderId
        room.population = population
        room.isFree = false
        roomRepository.save(room)
    }

    fun unBookRoom(id: Int) {
        val room: Room = this.getOne(id)
        room.orderField = null
        room.population = 0
        room.isFree = true
        roomRepository.save(room)
    }

    fun delete(id: Int): Int {
        roomRepository.deleteById(id)
        return id
    }

//    fun deleteWithBuilding(buildingId: Int) = roomRepository.deleteRoomByBuilding(buildingId)
//
//    fun deleteWithType(typeId: Int) = roomRepository.deleteRoomByType(typeId)
}