package loshica.api.hotel.interfaces

import loshica.api.hotel.models.User

interface IUserService : IBaseService<User> {
    fun getByEmail(email: String): User?
    fun countAmount(): Int
    fun create(email: String, password: String, role: String): User
    fun change(id: Int, role: String): User
}