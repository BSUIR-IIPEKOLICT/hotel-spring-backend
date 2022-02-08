package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IOrderService : IBaseService<Order> {
    fun get(basket: Basket): Iterable<Order>

    fun create(
        basket: Basket,
        room: Room,
        services: List<Service>,
        duty: Int,
        population: Int,
        date: String
    ): Order

    fun removeService(service: Service)
}