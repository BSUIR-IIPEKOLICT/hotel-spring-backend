package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import loshica.api.hotel.shared.Role

data class UserDto (
    val bookings: List<Int> = emptyList(),
    val email: String = "",
    val password: String = "",
    val role: String = Role.CLIENT,
    val isActive: Boolean = true,
    val id: Int = 0
) {

    @JsonGetter("isActive")
    fun parseIsActive(): Boolean = this.isActive
}