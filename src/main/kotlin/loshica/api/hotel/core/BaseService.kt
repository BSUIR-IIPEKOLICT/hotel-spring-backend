package loshica.api.hotel.core

import loshica.api.hotel.errors.EntityNotFoundError
import loshica.api.hotel.interfaces.IBaseService
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

abstract class BaseService<M, R : CrudRepository<M, Int>>(
    protected open val repository: R
) : IBaseService<M> {

    override fun getAll(): Iterable<M> = repository.findAll()

    override fun getOne(id: Int): M {
        val className: String = this.javaClass.name.split(".").last()

        return repository
            .findByIdOrNull(id)
            ?: throw EntityNotFoundError(
                entityName = className.replace("Service", ""),
                id = id
            )
    }

    override fun delete(id: Int): Int {
        getOne(id)?.let { repository.delete(it) }
        return id
    }
}