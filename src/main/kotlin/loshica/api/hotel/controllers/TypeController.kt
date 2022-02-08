package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.models.Type
import loshica.api.hotel.interfaces.*
import loshica.api.hotel.security.Auth
import loshica.api.hotel.shared.Role
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.type)
class TypeController(
    private val typeService: ITypeService,
    private val serviceService: IServiceService,
    private val roomService: IRoomService,
    private val reviewService: IReviewService,
    private val buildingService: IBuildingService,
    private val basketService: IBasketService,
    private val orderService: IOrderService
) {

    @GetMapping
    fun getAll(): Iterable<Type> = typeService.getAll()

    @PostMapping
    @ResponseBody
    fun create(
        @RequestBody dto: TypeDto,
        @RequestHeader authorization: String?
    ): Type {
        Auth.checkRoles(authorization, Role.adminOnly)

        return typeService.create(
            name = dto.name,
            places = dto.places,
            services = serviceService.getByIds(dto._services).toMutableList()
        )
    }

    @PatchMapping
    @ResponseBody
    fun change(
        @RequestBody dto: TypeDto,
        @RequestHeader authorization: String?
    ): Type {
        Auth.checkRoles(authorization, Role.adminOnly)

        val type: Type = typeService.getOne(dto._id.toInt())
        type.change(
            name = dto.name,
            places = dto.places,
            services = serviceService.getByIds(dto._services).toMutableList()
        )

        return type
    }

    @DeleteMapping
    fun delete(
        @RequestBody dto: TypeDto,
        @RequestHeader authorization: String?
    ): String {
        Auth.checkRoles(authorization, Role.adminOnly)

        val type: Type = typeService.getOne(dto._id.toInt())
        roomService.getByType(type).forEach {
            reviewService.deleteWithRoom(it)
            buildingService.removeRoom(it)

            it.orderField?.let { order ->
                basketService.removeOrder(order)
                orderService.delete(order.id)
            }

            roomService.delete(it.id)
        }

        return typeService.delete(id = type.id).toString()
    }
}