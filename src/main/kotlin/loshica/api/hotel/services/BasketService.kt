package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.errors.ApiError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.models.Basket
import loshica.api.hotel.models.Order
import loshica.api.hotel.models.User
import loshica.api.hotel.repositories.BasketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BasketService(
    @Autowired override val repository: BasketRepository
) : BaseService<Basket, BasketRepository>(repository) {

    fun getByUser(user: User): Basket = repository
        .findBasketByUser(user)
        ?: throw ApiError(ErrorMessage.notFound)

    fun create(user: User): Basket {
        val basket = Basket(user = user)
        repository.save(basket)
        return basket
    }

    fun addOrder(order: Order) {
        order.basket.let {
            it.orders.add(order)
            repository.save(it)
        }
    }

    fun removeOrder(order: Order) {
        order.basket.let {
            it.orders.remove(order)
            repository.save(it)
        }
    }

    fun deleteWithUser(user: User) {
        val basket: Basket = getByUser(user)
        repository.delete(basket)
    }
}