package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface ICommentService : IBaseService<Comment> {
    fun create(room: Room, content: String): Comment
    fun change(id: Int, content: String): Comment
    fun deleteWithRoom(room: Room)
}