package loshica.api.hotel.errors

class ApiError(
    override val message: String = ErrorMessage.unknownError
) : Exception(message)
