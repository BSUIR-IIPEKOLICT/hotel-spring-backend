package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class RoomPopulatedDto (
    @JsonProperty("building") val building: BuildingDto,
    @JsonProperty("type") val type: TypeDto,
    @JsonProperty("bookings") val bookings: List<BookingDto> = emptyList(),
    @JsonProperty("comments") val comments: List<CommentDto> = emptyList(),
    @JsonProperty("isFree") val isFree: Boolean = true,
    @JsonProperty("population") val population: Int = 0,
    @JsonProperty("bookedUntil") val bookedUntil: Date? = null,
    @JsonProperty("id") val id: Int = 0
) {

    @JsonGetter("isFree")
    fun parseIsFree(): Boolean = this.isFree
}