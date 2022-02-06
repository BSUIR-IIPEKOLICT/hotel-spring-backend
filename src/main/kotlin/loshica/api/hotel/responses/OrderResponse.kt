package loshica.api.hotel.responses

import loshica.api.hotel.models.Service

data class OrderResponse(
    val _basket: String = "",
    val _room: RoomResponse,
    val _services: List<Service> = emptyList(),
    val duty: Int = 0,
    val population: Int = 0,
    val date: String = "",
    val _id: String = ""
)
