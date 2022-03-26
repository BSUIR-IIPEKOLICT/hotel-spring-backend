package loshica.api.hotel.interfaces

import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.User

interface IUserService : IBaseService<User> {
    fun getByEmail(email: String): User?
    fun getAmount(): Int
    fun create(email: String, password: String, role: String): User
    fun change(id: Int, email: String, password: String, role: String): User
    fun addBooking(booking: Booking)
    fun removeBooking(booking: Booking)
}