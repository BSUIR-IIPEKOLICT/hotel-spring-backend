package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class TypePopulatedDto (
    @JsonProperty("rooms") val rooms: List<RoomDto> = emptyList(),
    @JsonProperty("name") val name: String = "",
    @JsonProperty("options") val options: String = "no options",
    @JsonProperty("price") val price: Int = 0,
    @JsonProperty("id") val id: Int = 0
)