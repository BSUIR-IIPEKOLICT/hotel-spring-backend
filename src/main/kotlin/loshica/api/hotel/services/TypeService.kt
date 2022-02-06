package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.models.Type
import loshica.api.hotel.repositories.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeService(
    @Autowired override val repository: TypeRepository
) : BaseService<Type, TypeRepository>(repository) {

    fun create(
        name: String,
        places: Int,
        services: MutableList<loshica.api.hotel.models.Service>
    ): Type {
        val type = Type(
            name = name,
            places = places,
            services = services
        )
        repository.save(type)
        return type
    }

    fun change(
        id: Int,
        name: String,
        places: Int,
        services: MutableList<loshica.api.hotel.models.Service>
    ): Type {
        val type: Type = getOne(id)
        type.change(name = name, places = places, services = services)
        repository.save(type)
        return type
    }
}