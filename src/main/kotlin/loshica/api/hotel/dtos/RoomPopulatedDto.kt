package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty

data class RoomPopulatedDto (
    @JsonProperty("type") val type: TypeDto,
    @JsonProperty("comments") val comments: List<CommentDto> = emptyList(),
    @JsonProperty("description") val description: String = "",
    @JsonProperty("address") val address: String = "",
    @JsonProperty("floor") val floor: Int = 1,
    @JsonProperty("places") val places: Int = 1,
    @JsonProperty("isFree") val isFree: Boolean = true,
    @JsonProperty("id") val id: Int = 0
) {

    @JsonGetter("isFree")
    fun parseIsFree(): Boolean = this.isFree
}