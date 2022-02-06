package loshica.api.hotel.responses

data class RoomResponse(
    val _building: String = "",
    val _type: String = "",
    val _order: String? = null,
    val isFree: Boolean = true,
    val population: Int = 0,
    val _id: String = ""
)
