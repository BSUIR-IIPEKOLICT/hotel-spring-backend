package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthDto (@JsonProperty("token") val token: String = "")
