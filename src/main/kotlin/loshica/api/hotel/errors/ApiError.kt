package loshica.api.hotel.errors

import org.springframework.http.HttpStatus

data class ApiError(
    val code: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val message: String = ErrorMessage.UNKNOWN_ERROR
)
