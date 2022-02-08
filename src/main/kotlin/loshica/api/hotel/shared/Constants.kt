package loshica.api.hotel.shared

object Constants {
    const val frontendUri = "http://localhost:3000"
    const val backendUri = "http://localhost:5000"
    const val roomLimit = 20
    const val orderEntity = "order_e"
    const val userEntity = "user_e"
    const val bcryptStrength: Int = 5
    const val threeHoursMillis: Int = 10_800_000
    const val jwtSecret = "abcdefghijklmnopqrstuvwxyz"
    const val minEmailChunks: Int = 3
    const val minPasswordLength: Int = 5
}