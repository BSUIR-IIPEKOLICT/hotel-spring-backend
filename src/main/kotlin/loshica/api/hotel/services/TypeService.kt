package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.interfaces.ITypeService
import loshica.api.hotel.models.Room
import loshica.api.hotel.models.Type
import loshica.api.hotel.repositories.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeService(
    @Autowired override val repository: TypeRepository
) : BaseService<Type, TypeRepository>(repository), ITypeService {

    override fun create(dto: TypeDto): Type {
        val type = Type(name = dto.name, options = dto.options, price = dto.price)
        repository.save(type)
        return type
    }

    override fun change(id: Int, dto: TypeDto): Type {
        val type: Type = getOne(id)

        type.name = dto.name
        type.options = dto.options
        type.price = dto.price

        return type
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
}