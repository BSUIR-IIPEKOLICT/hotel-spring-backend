package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonGetter
import java.util.Date

data class RoomDto (
    val building: Int = 0,
    val type: Int = 0,
    val bookings: List<Int> = emptyList(),
    val comments: List<Int> = emptyList(),
    val isFree: Boolean = true,
    val population: Int = 0,
    val bookedUntil: Date? = null,
    val id: Int = 0
) {

    @JsonGetter("isFree")
    fun parseIsFree(): Boolean = this.isFree
}