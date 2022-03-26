package loshica.api.hotel.core

import loshica.api.hotel.errors.ApiError
import org.springframework.http.HttpStatus

abstract class BaseError(
    val httpStatus: HttpStatus,
    val apiError: ApiError
) : RuntimeException(apiError.message)