package loshica.api.hotel.dtos

data class TypeDto (
    val rooms: List<Int> = emptyList(),
    val name: String = "",
    val options: String = "no options",
    val price: Int = 0,
    val id: Int = 0
)