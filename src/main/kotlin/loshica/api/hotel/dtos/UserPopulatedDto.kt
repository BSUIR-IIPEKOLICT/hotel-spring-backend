package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.Role

data class UserPopulatedDto (
    @JsonProperty("bookings") val bookings: List<BookingDto> = emptyList(),
    @JsonProperty("email") val email: String = "",
    @JsonProperty("password") val password: String = "",
    @JsonProperty("role") val role: String = Role.CLIENT,
    @JsonProperty("isActive") val isActive: Boolean = true,
    @JsonProperty("id") val id: Int = 0
) {

    @JsonGetter("isActive")
    fun parseIsActive(): Boolean = this.isActive
}