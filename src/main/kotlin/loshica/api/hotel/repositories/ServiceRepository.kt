package loshica.api.hotel.repositories

import loshica.api.hotel.models.Service
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository : CrudRepository<Service, Int>