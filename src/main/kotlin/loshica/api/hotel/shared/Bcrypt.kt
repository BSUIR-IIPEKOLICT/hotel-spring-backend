package loshica.api.hotel.shared

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object Bcrypt {
    private val instance = BCryptPasswordEncoder(Constants.bcryptStrength)

    fun hashPassword(password: String): String = instance.encode(password)

    fun comparePasswords(
        password: String,
        correct: String
    ): Boolean = instance.matches(password, correct)
}