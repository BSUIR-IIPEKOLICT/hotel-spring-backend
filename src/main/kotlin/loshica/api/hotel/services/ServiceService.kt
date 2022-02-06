package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.repositories.ServiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceService(
    @Autowired override val repository: ServiceRepository
) : BaseService<loshica.api.hotel.models.Service, ServiceRepository>(repository) {

    fun getByIds(
        ids: List<String>
    ): Iterable<loshica.api.hotel.models.Service> = ids.map { getOne(it.toInt()) }

    fun create(name: String, price: Int): loshica.api.hotel.models.Service {
        val service = loshica.api.hotel.models.Service(name = name, price = price)
        repository.save(service)
        return service
    }

    fun change(id: Int, name: String, price: Int): loshica.api.hotel.models.Service {
        val service: loshica.api.hotel.models.Service = this.getOne(id)
        service.change(name = name, price = price)
        repository.save(service)
        return service
    }
}