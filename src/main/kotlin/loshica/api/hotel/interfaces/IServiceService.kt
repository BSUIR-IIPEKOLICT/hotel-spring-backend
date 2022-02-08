package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IServiceService : IBaseService<Service> {
    fun getByIds(ids: List<String>): Iterable<Service>
    fun create(name: String, price: Int): Service
    fun change(id: Int, name: String, price: Int): Service
}