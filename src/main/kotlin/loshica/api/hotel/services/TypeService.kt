package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.ITypeService
import loshica.api.hotel.models.Option
import loshica.api.hotel.models.Room
import loshica.api.hotel.models.Type
import loshica.api.hotel.repositories.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TypeService(
    @Autowired override val repository: TypeRepository
) : BaseService<Type, TypeRepository>(repository), ITypeService {

    override fun create(
        name: String,
        places: Int,
        price: Int,
        options: List<Option>
    ): Type {
        val type = Type(
            name = name,
            places = places,
            price = price,
            options = options.toMutableList()
        )
        repository.save(type)
        return type
    }

    override fun change(
        id: Int,
        name: String,
        places: Int,
        price: Int,
        options: List<Option>
    ): Type {
        val type: Type = getOne(id)
        type.change(name = name, places = places, price = price, options = options)
        repository.save(type)
        return type
    }

    override fun removeOption(option: Option) = getAll().forEach {
        it.options.remove(option)
        repository.save(it)
    }

    override fun addRoom(room: Room) {
        room.type.let {
            it.rooms.add(room)
            repository.save(it)
        }
    }

    override fun removeRoom(room: Room) {
        room.type.let {
            it.rooms.remove(room)
            repository.save(it)
        }
    }

    override fun clearOptions(type: Type) {
        type.options = mutableListOf()
        repository.save(type)
    }

    override fun disable(id: Int): Int {
        val type: Type = getOne(id)
        type.isActive = false
        type.name = "${Date().time}-${Math.random()}"
        repository.save(type)
        return id
    }
}