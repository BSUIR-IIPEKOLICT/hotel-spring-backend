package loshica.api.hotel.shared

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import loshica.api.hotel.models.User
import java.util.*

object Jwt {
    fun generateToken(user: User): String = Jwts.builder()
        .setClaims(mapOf(
            FieldName.id to user.id.toString(),
            FieldName.email to user.email,
            FieldName.role to user.role
        ))
        .signWith(SignatureAlgorithm.HS512, Constants.jwtSecret)
        .setExpiration(Date(System.currentTimeMillis() + Constants.threeHoursMillis))
        .compact()

    fun decode(token: String): UserData {
        val payloadClaims: Claims = Jwts
            .parser()
            .setSigningKey(Constants.jwtSecret)
            .parseClaimsJws(token)
            .body

        return UserData(
            _id = payloadClaims[FieldName.id].toString(),
            email = payloadClaims[FieldName.email].toString(),
            role = payloadClaims[FieldName.role].toString()
        )
    }
}