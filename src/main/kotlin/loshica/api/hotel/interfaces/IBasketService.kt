package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IBasketService : IBaseService<Basket> {
    fun getByUser(user: User): Basket
    fun create(user: User): Basket
    fun addOrder(order: Order)
    fun removeOrder(order: Order)
}