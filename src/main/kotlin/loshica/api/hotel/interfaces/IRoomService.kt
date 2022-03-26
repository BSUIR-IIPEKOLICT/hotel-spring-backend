package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IRoomService : IBaseService<Room> {
    fun get(
        buildingId: Int?,
        typeId: Int?,
        isFree: Boolean?,
        limit: Int,
        offset: Int
    ): Iterable<Room>

    fun getAmount(buildingId: Int?, typeId: Int?, isFree: Boolean?): Int
    fun create(building: Building, type: Type): Room
    fun change(id: Int, building: Building, type: Type): Room
    fun bookRoom(booking: Booking)
    fun unBookRoom(booking: Booking)
    fun addComment(comment: Comment)
    fun removeComment(comment: Comment)
}