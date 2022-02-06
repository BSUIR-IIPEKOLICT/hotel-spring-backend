package loshica.api.hotel.services

import loshica.api.hotel.models.Type
import loshica.api.hotel.repositories.TypeRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TypeService(@Autowired private val typeRepository: TypeRepository) {

    fun getAll(): Iterable<Type> = typeRepository.findAll()

    fun getOne(id: Int): Type = typeRepository
        .findByIdOrNull(id)
        ?: throw Exception(Constants.notFoundMessage)

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
        typeRepository.save(type)
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
        typeRepository.save(type)
        return type
    }

    fun delete(id: Int): Int {
        typeRepository.deleteById(id)
        return id
    }
}