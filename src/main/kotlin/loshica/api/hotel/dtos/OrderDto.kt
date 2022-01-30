package loshica.api.hotel.dtos

data class OrderDto (
    val _basket: String = "",
    val _room: String = "",
    val _services: String = "",
    val duty: Int = 0,
    val population: Int = 0,
    val date: String = "",
    val _id: String = ""
)