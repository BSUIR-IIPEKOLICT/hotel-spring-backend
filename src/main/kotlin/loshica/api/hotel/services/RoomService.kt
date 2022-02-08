package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IRoomService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomService(
    @Autowired override val repository: RoomRepository
) : BaseService<Room, RoomRepository>(repository), IRoomService {

    private fun getByQuery(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?
    ): Iterable<Room> {
        var rooms: Iterable<Room> = repository.findAll()

        buildingId?.let {
            rooms = rooms.filter { room -> room.building.id == it.toInt() }
        }

        typeId?.let {
            rooms = rooms.filter { room -> room.type.id == it.toInt() }
        }

        isFree?.let {
            rooms = rooms.filter { room -> room.isFree == it }
        }

        return rooms
    }

    override fun get(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?,
        limit: Int,
        offset: Int
    ): Iterable<Room> {
        val rooms: Iterable<Room> = getByQuery(buildingId, typeId, isFree)

        return rooms
            .withIndex()
            .filter { indexedValue: IndexedValue<Room> ->
                indexedValue.index >= offset && indexedValue.index <= offset + limit
            }
            .map { indexedValue: IndexedValue<Room> -> indexedValue.value }
    }

    override fun getAmount(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?
    ): Int = getByQuery(buildingId, typeId, isFree).count()

    override fun getByBuilding(building: Building): Iterable<Room> = repository
        .findByBuilding(building)

    override fun getByType(type: Type): Iterable<Room> = repository.findByType(type)

    override fun create(building: Building, type: Type): Room {
        val room = Room(building = building, type = type)
        repository.save(room)
        return room
    }

    override fun change(id: Int, building: Building, type: Type): Room {
        val room: Room = getOne(id)
        room.change(building = building, type = type)
        repository.save(room)
        return room
    }

    override fun bookRoom(order: Order) {
        order.room.let {
            it.book(order = order, population = order.population)
            repository.save(it)
        }
    }

    override fun unBookRoom(id: Int) {
        getOne(id).let {
            it.unBook()
            repository.save(it)
        }
    }
}