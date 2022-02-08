package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface ITypeService : IBaseService<Type> {
    fun create(
        name: String,
        places: Int,
        services: MutableList<Service>
    ): Type

    fun change(
        id: Int,
        name: String,
        places: Int,
        services: MutableList<Service>
    ): Type

    fun removeService(service: Service)
}