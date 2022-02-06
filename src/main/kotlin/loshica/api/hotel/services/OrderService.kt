package loshica.api.hotel.services

import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.OrderRepository
import loshica.api.hotel.shared.BaseOrderDto
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired private val orderRepository: OrderRepository) {

    fun get(basket: Int): Iterable<Order> = orderRepository.findByBasket(
        basket = basket
    )

    fun getOne(id: Int): Order = orderRepository
        .findByIdOrNull(id)
        ?: throw Exception(Constants.notFoundMessage)

    fun create(
        basket: Int,
        room: Room,
        services: List<loshica.api.hotel.models.Service>,
        duty: Int,
        population: Int,
        date: String
    ): BaseOrderDto {
        val order = Order(
            basket = basket,
            room = room,
            services = services.toMutableList(),
            duty = duty,
            population = population,
            date = date
        )
        orderRepository.save(order)
        return order.convertToBaseDto()
    }

    fun delete(id: Int): Int {
        orderRepository.deleteById(id)
        return id
    }

    fun deleteWithBasket(basket: Int) = orderRepository.deleteByBasket(basket)
}