package loshica.api.hotel.dtos

data class RoomDto (
    val _building: String = "",
    val _type: String = "",
    val _order: String? = null,
    val isFree: Boolean = true,
    val population: Int = 0,
    val _id: String = ""
)