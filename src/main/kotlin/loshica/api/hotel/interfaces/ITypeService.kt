package loshica.api.hotel.interfaces

import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.models.*

interface ITypeService : IBaseService<Type> {
    fun create(dto: TypeDto): Type
    fun change(id: Int, dto: TypeDto): Type
    fun addRoom(room: Room)
    fun removeRoom(room: Room)
}