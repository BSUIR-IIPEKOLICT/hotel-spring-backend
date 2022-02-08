package loshica.api.hotel.security

import loshica.api.hotel.shared.Role

data class UserData (
    val _id: String = "",
    val email: String = "",
    val role: String = Role.client
)