package loshica.api.hotel.interfaces

import loshica.api.hotel.models.Building
import loshica.api.hotel.models.Room

interface IBuildingService : IBaseService<Building> {
    fun create(address: String): Building
    fun change(id: Int, address: String): Building
    fun addRoom(room: Room)
    fun removeRoom(room: Room)
}