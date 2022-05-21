package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IOptionService : IBaseService<Option> {
    fun getByIds(ids: List<Int>): List<Option>
    fun create(name: String, price: Int): Option
    fun change(id: Int, name: String, price: Int): Option
    fun addBooking(booking: Booking)
    fun removeBooking(booking: Booking)
    fun addType(type: Type)
    fun removeType(type: Type)
    fun disable(id: Int): Int
}