package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomService(
    @Autowired override val repository: RoomRepository
) : BaseService<Room, RoomRepository>(repository) {

    private fun getByQuery(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?
    ): Iterable<Room> {
        var rooms: Iterable<Room> = repository.findAll()

        if (buildingId != null) {
            rooms = rooms.filter {
                room: Room -> room.building.id == buildingId.toInt()
            }
        }

        if (typeId != null) {
            rooms = rooms.filter {
                room: Room -> room.type.id == typeId.toInt()
            }
        }

        if (isFree != null) {
            rooms = rooms.filter {
                room: Room -> room.isFree == isFree
            }
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
        val rooms: Iterable<Room> = getByQuery(buildingId, typeId, isFree)

        return rooms
            .withIndex()
            .filter { indexedValue: IndexedValue<Room> ->
                indexedValue.index >= offset && indexedValue.index <= offset + limit
            }
            .map { indexedValue: IndexedValue<Room> -> indexedValue.value }
    }

    fun getAmount(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?
    ): Int = getByQuery(buildingId, typeId, isFree).count()

    fun getByBuilding(building: Building): Iterable<Room> = repository
        .findByBuilding(building)

    fun create(building: Building, type: Type): Room {
        val room = Room(building = building, type = type)
        repository.save(room)
        return room
    }

    fun change(id: Int, building: Building, type: Type): Room {
        val room: Room = this.getOne(id)
        room.change(building = building, type = type)
        repository.save(room)
        return room
    }

    fun bookRoom(id: Int, order: Order, population: Int) {
        val room: Room = this.getOne(id)
        room.book(order = order, population = population)
        repository.save(room)
    }

    fun unBookRoom(id: Int) {
        val room: Room = this.getOne(id)
        room.unBook()
        repository.save(room)
    }
}