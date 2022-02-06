package loshica.api.hotel.repositories

import loshica.api.hotel.models.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Int> {
    fun findByBasket(basket: Int): Iterable<Order>
    fun deleteByBasket(basket: Int)
}