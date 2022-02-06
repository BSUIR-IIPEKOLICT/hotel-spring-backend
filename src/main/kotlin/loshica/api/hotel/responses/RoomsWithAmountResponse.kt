package loshica.api.hotel.responses

import loshica.api.hotel.models.Room

data class RoomsWithAmountResponse(
    val rooms: Iterable<Room>,
    val amount: Int
)
