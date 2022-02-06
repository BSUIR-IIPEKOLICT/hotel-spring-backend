package loshica.api.hotel.services

import loshica.api.hotel.models.Building
import loshica.api.hotel.repositories.BuildingRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BuildingService(@Autowired private val buildingRepository: BuildingRepository) {

    fun getAll(): Iterable<Building> = buildingRepository.findAll()

    fun getOne(id: Int): Building = buildingRepository
        .findByIdOrNull(id)
        ?: throw Exception(Constants.notFoundMessage)

    fun create(address: String): Building {
        val building = Building(address = address)
        buildingRepository.save(building)
        return building
    }

    fun change(id: Int, address: String): Building {
        val building: Building = this.getOne(id)
        building.change(address = address)
        buildingRepository.save(building)
        return building
    }

    fun delete(id: Int): Int {
        buildingRepository.deleteById(id)
        return id
    }
}