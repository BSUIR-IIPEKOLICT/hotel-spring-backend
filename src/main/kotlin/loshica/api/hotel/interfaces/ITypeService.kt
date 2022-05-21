package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface ITypeService : IBaseService<Type> {
    fun create(
        name: String,
        places: Int,
        price: Int,
        options: List<Option>
    ): Type

    fun change(
        id: Int,
        name: String,
        places: Int,
        price: Int,
        options: List<Option>
    ): Type

    fun removeOption(option: Option)
    fun addRoom(room: Room)
    fun removeRoom(room: Room)
    fun clearOptions(type: Type)
    fun disable(id: Int): Int
}