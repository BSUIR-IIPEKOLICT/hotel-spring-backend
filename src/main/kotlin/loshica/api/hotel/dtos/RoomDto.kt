package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter

data class RoomDto (
    val type: Int = 0,
    val comments: List<Int> = emptyList(),
    val description: String = "",
    val address: String = "",
    val floor: Int = 1,
    val places: Int = 1,
    val isFree: Boolean = true,
    val id: Int = 0
) {

    @JsonGetter("isFree")
    fun parseIsFree(): Boolean = this.isFree
}