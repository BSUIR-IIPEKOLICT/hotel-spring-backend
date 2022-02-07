package loshica.api.hotel.shared

data class UserData (
    val _id: String = "",
    val email: String = "",
    val role: String = Role.client
)