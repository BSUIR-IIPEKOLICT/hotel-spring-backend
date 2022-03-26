package loshica.api.hotel.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class BookingDto (
    val room: Int = 0,
    val createdBy: Int = 0,
    val options: List<Int> = emptyList(),
    val price: Int = 0,
    val population: Int = 0,
    val startDate: Date? = null,
    val endDate: Date? = null,
    @JsonProperty("isActive") val isActive: Boolean = true,
    val id: Int = 0
)