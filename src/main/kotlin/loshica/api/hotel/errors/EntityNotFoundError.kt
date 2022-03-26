package loshica.api.hotel.errors

import loshica.api.hotel.core.BaseError
import org.springframework.http.HttpStatus

class EntityNotFoundError(private val entityName: String, private val id: Int) : BaseError(
    HttpStatus.NOT_FOUND,
    ApiError(
        code = HttpStatus.NOT_FOUND,
        message = "$entityName with id '$id' not found"
    )
)