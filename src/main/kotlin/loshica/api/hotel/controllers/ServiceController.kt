package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.ServiceDto
import loshica.api.hotel.interfaces.IOrderService
import loshica.api.hotel.interfaces.IServiceService
import loshica.api.hotel.interfaces.ITypeService
import loshica.api.hotel.models.Service
import loshica.api.hotel.security.Auth
import loshica.api.hotel.shared.Role
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.service)
class ServiceController(
    private val serviceService: IServiceService,
    private val typeService: ITypeService,
    private val orderService: IOrderService
) {

    @GetMapping
    fun getAll(): Iterable<Service> = serviceService.getAll()

    @PostMapping
    @ResponseBody
    fun create(
        @RequestBody dto: ServiceDto,
        @RequestHeader authorization: String?
    ): Service {
        Auth.checkRoles(authorization, Role.adminOnly)

        return serviceService.create(
            name = dto.name,
            price = dto.price
        )
    }

    @PatchMapping
    @ResponseBody
    fun change(
        @RequestBody dto: ServiceDto,
        @RequestHeader authorization: String?
    ): Service {
        Auth.checkRoles(authorization, Role.adminOnly)

        return serviceService.change(
            id = dto._id.toInt(),
            name = dto.name,
            price = dto.price
        )
    }

    @DeleteMapping
    fun delete(
        @RequestBody dto: ServiceDto,
        @RequestHeader authorization: String?
    ): String {
        Auth.checkRoles(authorization, Role.adminOnly)

        val service: Service = serviceService.getOne(dto._id.toInt())
        typeService.removeService(service)
        orderService.removeService(service)

        return serviceService
            .delete(id = dto._id.toInt())
            .toString()
    }
}