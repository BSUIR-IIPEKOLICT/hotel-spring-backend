package loshica.api.hotel.core

import loshica.api.hotel.errors.ApiError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.interfaces.IBaseService
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

open class BaseService<M, R : CrudRepository<M, Int>>(
    protected open val repository: R
) : IBaseService<M> {

    override fun getAll(): Iterable<M> = repository.findAll()

    override fun getOne(id: Int): M = repository
        .findByIdOrNull(id)
        ?: throw ApiError(ErrorMessage.notFound)

    override fun delete(id: Int): Int {
        repository.deleteById(id)
        return id
    }
}