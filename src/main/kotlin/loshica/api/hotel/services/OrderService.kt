package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.OrderRepository
import loshica.api.hotel.shared.BaseOrderDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired override val repository: OrderRepository
) : BaseService<Order, OrderRepository>(repository) {

    fun get(basket: Basket): Iterable<Order> = repository.findByBasket(
        basket = basket
    )

    fun create(
        basket: Basket,
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
        repository.save(order)
        return order.convertToBaseDto()
    }

    fun deleteWithBasket(basket: Basket) {
        repository.findByBasket(basket).forEach { repository.delete(it) }
    }

    fun deleteWithRoom(room: Room) {
        repository.findByRoom(room).forEach { repository.delete(it) }
    }
}