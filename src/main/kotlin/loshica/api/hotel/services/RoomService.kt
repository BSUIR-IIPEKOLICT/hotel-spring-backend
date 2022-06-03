package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.interfaces.IRoomService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomService(
    @Autowired override val repository: RoomRepository
) : BaseService<Room, RoomRepository>(repository), IRoomService {

    override fun create(type: Type, dto: RoomDto): Room {
        val room = Room(
            type = type,
            description = dto.description,
            address = dto.address,
            floor = dto.floor,
            places = dto.places
        )

        repository.save(room)
        return room
    }

    override fun change(id: Int, dto: RoomDto): Room {
        val room = getOne(id)

        room.description = dto.description
        room.address = dto.address
        room.floor = dto.floor
        room.places = dto.places

        repository.save(room)
        return room
    }

    override fun bookRoom(roomId: Int) {
        val room: Room = getOne(roomId)
        room.isFree = false
        repository.save(room)
    }

    override fun unBookRoom(roomId: Int) {
        val room: Room = getOne(roomId)
        room.isFree = true
        repository.save(room)
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