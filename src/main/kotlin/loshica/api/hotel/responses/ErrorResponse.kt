package loshica.api.hotel.responses

import loshica.api.hotel.errors.ErrorMessage

data class ErrorResponse(
    var status : Int? = null,
    val message: String = ErrorMessage.unknownError
)
