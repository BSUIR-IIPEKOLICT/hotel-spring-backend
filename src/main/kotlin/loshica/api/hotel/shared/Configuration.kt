package loshica.api.hotel.shared

object Configuration {
    private val ENV = System.getenv() ?: emptyMap()

    val JWT_SECRET: String = ENV["JWT_SECRET"] ?: "abcdefghijklmnopqrstuvwxyz"
    val JWT_EXPIRATION_MILLIS: Int = (ENV["JWT_EXPIRATION_HOURS"]?.toInt() ?: 24) * 3_600_000
    val ROOM_LIMIT: Int = ENV["ROOM_LIMIT"]?.toInt() ?: 20
    val BCRYPT_STRENGTH = ENV["BCRYPT_STRENGTH"]?.toInt() ?: 5
    val MIN_EMAIL_CHUNKS = ENV["MIN_EMAIL_CHUNKS"]?.toInt() ?: 3
    val MIN_PASSWORD_LENGTH = ENV["MIN_PASSWORD_LENGTH"]?.toInt() ?: 5
    val TOKEN_TYPE_KEY = ENV["TOKEN_TYPE_KEY"] ?: "typ"
    val TOKEN_TYPE_VALUE = ENV["TOKEN_TYPE_VALUE"] ?: "JWT"
}