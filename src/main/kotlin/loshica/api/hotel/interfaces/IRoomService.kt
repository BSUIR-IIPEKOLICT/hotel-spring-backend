package loshica.api.hotel.interfaces

import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.models.*

interface IRoomService : IBaseService<Room> {
    fun create(type: Type, dto: RoomDto): Room
    fun change(id: Int, type: Type, isFree: Boolean, dto: RoomDto): Room
    fun addComment(comment: Comment)
    fun removeComment(comment: Comment)
}