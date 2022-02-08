package loshica.api.hotel.repositories

import loshica.api.hotel.models.Type
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository : CrudRepository<Type, Int>