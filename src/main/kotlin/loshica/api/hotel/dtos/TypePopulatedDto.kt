package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class TypePopulatedDto (
    @JsonProperty("options") val options: List<OptionDto> = emptyList(),
    @JsonProperty("rooms") val rooms: List<RoomDto> = emptyList(),
    @JsonProperty("name") val name: String = "",
    @JsonProperty("places") val places: Int = 0,
    @JsonProperty("price") val price: Int = 0,
    @JsonProperty("id") val id: Int = 0
)