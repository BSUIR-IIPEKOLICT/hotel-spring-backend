package loshica.api.hotel.dtos

data class BuildingDto (
    val rooms: List<Int> = emptyList(),
    val address: String = "",
    val id: Int = 0
)