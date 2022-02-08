package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IOrderService
import loshica.api.hotel.models.*
import loshica.api.hotel.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired override val repository: OrderRepository
) : BaseService<Order, OrderRepository>(repository), IOrderService {

    override fun get(basket: Basket): Iterable<Order> = repository.findByBasket(
        basket = basket
    )

    override fun create(
        basket: Basket,
        room: Room,
        services: List<loshica.api.hotel.models.Service>,
        duty: Int,
        population: Int,
        date: String
    ): Order {
        val order = Order(
            basket = basket,
            room = room,
            services = services.toMutableList(),
            duty = duty,
            population = population,
            date = date
        )
        repository.save(order)
        return order
    }

    override fun removeService(
        service: loshica.api.hotel.models.Service
    ) = getAll().forEach { it.services.remove(service) }
}