package loshica.api.hotel.errors

import loshica.api.hotel.core.BaseError
import org.springframework.http.HttpStatus

class BadRequestError(
    override val message: String = ErrorMessage.BAD_REQUEST
) : BaseError(
    HttpStatus.BAD_REQUEST,
    ApiError(
        code = HttpStatus.BAD_REQUEST,
        message = message
    )
)