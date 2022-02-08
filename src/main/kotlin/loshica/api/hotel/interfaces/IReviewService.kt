package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IReviewService : IBaseService<Review> {
    fun getByRoom(room: Room): Iterable<Review>
    fun create(roomId: Room, author: String, content: String): Review
    fun change(id: Int, content: String): Review
    fun deleteWithRoom(room: Room)
}