package loshica.api.hotel.repositories

import loshica.api.hotel.models.Basket
import loshica.api.hotel.models.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Int> {
    fun findByBasket(basket: Basket): Iterable<Order>
}