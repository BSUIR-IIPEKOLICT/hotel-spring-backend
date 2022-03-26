package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class BookingResponseDto(
    @JsonProperty("active") val active: List<BookingPopulatedDto> = emptyList(),
    @JsonProperty("inActive") val inActive: List<BookingPopulatedDto> = emptyList()
)
