package loshica.api.hotel.dtos

import loshica.api.hotel.shared.Constants

data class UserDto (
    val email: String = "",
    val password: String = "",
    val role: String = Constants.defaultRole,
    val _id: String = ""
)