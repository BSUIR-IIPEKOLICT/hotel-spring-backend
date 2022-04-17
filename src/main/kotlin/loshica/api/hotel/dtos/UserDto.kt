package loshica.api.hotel.dtos

import loshica.api.hotel.shared.Role
import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto (
    val bookings: List<Int> = emptyList(),
    val email: String = "",
    val password: String = "",
    val role: String = Role.CLIENT,
    @JsonProperty("isActive") val isActive: Boolean = true,
    val id: Int = 0
)