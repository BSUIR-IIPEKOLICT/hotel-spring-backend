package loshica.api.hotel.interfaces

import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.models.*

interface IRoomService : IBaseService<Room> {
    fun create(type: Type, dto: RoomDto): Room
    fun change(id: Int, dto: RoomDto): Room
    fun bookRoom(roomId: Int)
    fun unBookRoom(roomId: Int)
    fun addComment(comment: Comment)
    fun removeComment(comment: Comment)
}