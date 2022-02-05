package loshica.api.hotel.services

import loshica.api.hotel.repositories.ServiceRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ServiceService(@Autowired private val serviceRepository: ServiceRepository) {

    fun getAll(): Iterable<loshica.api.hotel.models.Service> = serviceRepository.findAll()

    fun getOne(id: Int): loshica.api.hotel.models.Service = serviceRepository
        .findByIdOrNull(id) ?: throw Exception(Constants.notFoundMessage)

    fun create(name: String, price: Int): loshica.api.hotel.models.Service {
        val service = loshica.api.hotel.models.Service(name = name, price = price)
        serviceRepository.save(service)
        return service
    }

    fun change(id: Int, name: String, price: Int): loshica.api.hotel.models.Service {
        val service: loshica.api.hotel.models.Service = this.getOne(id)
        service.name = name
        service.price = price
        serviceRepository.save(service)
        return service
    }

    fun delete(id: Int): Int {
        serviceRepository.deleteById(id)
        return id
    }
}