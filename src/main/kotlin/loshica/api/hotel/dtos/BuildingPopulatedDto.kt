package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class BuildingPopulatedDto (
    @JsonProperty("rooms") val rooms: List<RoomDto> = emptyList(),
    @JsonProperty("address") val address: String = "",
    @JsonProperty("id") val id: Int = 0
)