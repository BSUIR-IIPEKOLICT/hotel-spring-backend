package loshica.api.hotel.dtos

data class TypeDto (
    val options: List<Int> = emptyList(),
    val rooms: List<Int> = emptyList(),
    val name: String = "",
    val places: Int = 0,
    val price: Int = 0,
    val id: Int = 0
)