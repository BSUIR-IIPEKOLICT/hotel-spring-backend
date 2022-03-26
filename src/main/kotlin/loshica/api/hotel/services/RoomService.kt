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
        buildingId: Int?,
        typeId: Int?,
        isFree: Boolean?
    ): Iterable<Room> {
        var rooms: Iterable<Room> = repository.findAll()

        buildingId?.let {
            rooms = rooms.filter { room -> room.building.id == it }
        }

        typeId?.let {
            rooms = rooms.filter { room -> room.type.id == it }
        }

        isFree?.let {
            rooms = rooms.filter { room -> room.isFree == it }
        }

        return rooms
    }

    override fun get(
        buildingId: Int?,
        typeId: Int?,
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

    override fun getAmount(buildingId: Int?, typeId: Int?, isFree: Boolean?): Int {
        return getByQuery(buildingId, typeId, isFree).count()
    }

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

    override fun bookRoom(booking: Booking) {
        booking.room.let {
            it.book(booking)
            repository.save(it)
        }
    }

    override fun unBookRoom(booking: Booking) {
        booking.room.let {
            it.unBook(booking)
            repository.save(it)
        }
    }

    override fun addComment(comment: Comment) {
        comment.room.let {
            it.comments.add(comment)
            repository.save(it)
        }
    }

    override fun removeComment(comment: Comment) {
        comment.room.let {
            it.comments.remove(comment)
            repository.save(it)
        }
    }
}