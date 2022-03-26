package loshica.api.hotel.shared

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import loshica.api.hotel.dtos.TokenDto
import loshica.api.hotel.models.User
import java.util.*

object Jwt {
    private const val ID_KEY = "id"
    private const val EMAIL_KEY = "email"
    private const val ROLE_KEY = "role"

    private fun getExpiration(): Date = Date(
        System.currentTimeMillis() + Configuration.JWT_EXPIRATION_MILLIS
    )

    fun generateToken(user: User): String = Jwts.builder()
        .setClaims(
            mapOf(
                ID_KEY to user.id,
                EMAIL_KEY to user.email,
                ROLE_KEY to user.role
            )
        )
        .signWith(SignatureAlgorithm.HS256, Configuration.JWT_SECRET)
        .setHeaderParam(Configuration.TOKEN_TYPE_KEY, Configuration.TOKEN_TYPE_VALUE)
        .setExpiration(getExpiration())
        .compact()

    fun decode(token: String): TokenDto {
        val payloadClaims: Claims = Jwts
            .parser()
            .setSigningKey(Configuration.JWT_SECRET)
            .parseClaimsJws(token)
            .body

        return TokenDto(
            id = payloadClaims[ID_KEY].toString().toInt(),
            email = payloadClaims[EMAIL_KEY].toString(),
            role = payloadClaims[ROLE_KEY].toString()
        )
    }
}