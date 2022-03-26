package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class RoomPaginationDto(
    @JsonProperty("amount") val amount: Int = 0,
    @JsonProperty("rooms") val rooms: List<RoomPopulatedDto> = emptyList()
)
