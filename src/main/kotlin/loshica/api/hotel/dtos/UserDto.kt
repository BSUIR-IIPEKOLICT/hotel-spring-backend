package loshica.api.hotel.dtos

import loshica.api.hotel.shared.Role

data class UserDto (
    val email: String = "",
    val password: String = "",
    val role: String = Role.client,
    val _id: String = ""
)