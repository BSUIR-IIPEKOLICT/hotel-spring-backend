package loshica.api.hotel.errors

object ErrorMessage {
    const val notFound = "not found"
    const val badRequest = "bad request"
    const val unknownError = "unknown error"
    const val invalidEmail = "invalid email"
    const val invalidPassword = "invalid password"
    const val shortPassword = "bad password, use at least 5 any symbols"
    const val userExists = "user already exists"
    const val noUser = "no user with this email"
    const val unauthorized = "unauthorized"
    const val noAccess = "no access"
}