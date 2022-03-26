package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.Role

data class TokenDto (
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("email") val email: String = "",
    @JsonProperty("role") val role: String = Role.CLIENT
)