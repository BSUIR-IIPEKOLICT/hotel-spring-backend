package loshica.api.hotel.repositories

import loshica.api.hotel.models.Building
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BuildingRepository : CrudRepository<Building, Int>