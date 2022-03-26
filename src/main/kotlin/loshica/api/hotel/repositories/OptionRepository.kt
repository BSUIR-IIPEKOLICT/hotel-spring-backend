package loshica.api.hotel.repositories

import loshica.api.hotel.models.Option
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OptionRepository : CrudRepository<Option, Int> {
    fun findByIdIsIn(ids: MutableCollection<Int>): Iterable<Option>
}