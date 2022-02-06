package loshica.api.hotel.dtos

data class TypeDto (
    val _services: List<String> = emptyList(),
    val name: String = "",
    val places: Int = 0,
    val _id: String = ""
)