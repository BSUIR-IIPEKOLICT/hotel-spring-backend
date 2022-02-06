package loshica.api.hotel.shared

import loshica.api.hotel.models.Service

data class BaseOrderDto(
    val _basket: String = "",
    val _room: BaseRoomDto,
    val _services: List<Service> = emptyList(),
    val duty: Int = 0,
    val population: Int = 0,
    val date: String = "",
    val _id: String = ""
)
