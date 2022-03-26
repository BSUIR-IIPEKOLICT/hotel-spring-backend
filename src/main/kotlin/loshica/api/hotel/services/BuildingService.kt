package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IBuildingService
import loshica.api.hotel.models.Building
import loshica.api.hotel.models.Room
import loshica.api.hotel.repositories.BuildingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BuildingService(
    @Autowired override val repository: BuildingRepository
) : BaseService<Building, BuildingRepository>(repository), IBuildingService {

    override fun create(address: String): Building {
        val building = Building(address = address)
        repository.save(building)
        return building
    }

    override fun change(id: Int, address: String): Building {
        val building: Building = getOne(id)
        building.address = address
        repository.save(building)
        return building
    }

    override fun addRoom(room: Room) {
        room.building.let {
            it.rooms.add(room)
            repository.save(it)
        }
    }

    override fun removeRoom(room: Room) {
        room.building.let {
            it.rooms.remove(room)
            repository.save(it)
        }
    }
}
