package loshica.api.hotel.errors

import loshica.api.hotel.core.BaseError
import org.springframework.http.HttpStatus

class UnauthorizedError(
    override val message: String = ErrorMessage.UNAUTHORIZED
) : BaseError(
    HttpStatus.UNAUTHORIZED,
    ApiError(
        code = HttpStatus.UNAUTHORIZED,
        message = message
    )
)