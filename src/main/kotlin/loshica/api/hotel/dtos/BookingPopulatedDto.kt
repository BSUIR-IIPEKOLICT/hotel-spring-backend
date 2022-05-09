package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class BookingPopulatedDto (
    @JsonProperty("room") val room: RoomDto,
    @JsonProperty("createdBy") val createdBy: UserDto,
    @JsonProperty("options") val options: List<OptionDto> = emptyList(),
    @JsonProperty("price") val price: Int = 0,
    @JsonProperty("population") val population: Int = 0,
    @JsonProperty("startDate") val startDate: Date? = null,
    @JsonProperty("endDate") val endDate: Date? = null,
    @JsonProperty("isActive") val isActive: Boolean = true,
    @JsonProperty("id") val id: Int = 0
) {

    @JsonGetter("isActive")
    fun parseIsActive(): Boolean = this.isActive
}