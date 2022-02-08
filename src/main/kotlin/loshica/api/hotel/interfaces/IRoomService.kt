package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IRoomService : IBaseService<Room> {
    fun get(
        buildingId: String?,
        typeId: String?,
        isFree: Boolean?,
        limit: Int,
        offset: Int
    ): Iterable<Room>

    fun getAmount(buildingId: String?, typeId: String?, isFree: Boolean?): Int
    fun getByBuilding(building: Building): Iterable<Room>
    fun getByType(type: Type): Iterable<Room>
    fun create(building: Building, type: Type): Room
    fun change(id: Int, building: Building, type: Type): Room
    fun bookRoom(order: Order)
    fun unBookRoom(id: Int)
}