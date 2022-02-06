package loshica.api.hotel.repositories

import loshica.api.hotel.models.Basket
import loshica.api.hotel.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BasketRepository : CrudRepository<Basket, Int> {
    fun findByUser(user: User): Iterable<Basket>
}