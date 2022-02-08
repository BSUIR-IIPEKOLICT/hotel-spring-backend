package loshica.api.hotel.security

import loshica.api.hotel.errors.ApiError
import loshica.api.hotel.errors.ErrorMessage
import loshica.api.hotel.shared.Role

object Auth {
    fun getData(authorizationField: String?): UserData {
        try {
            val token: String = authorizationField
                ?.split(" ")
                ?.get(1)
            ?: throw ApiError(ErrorMessage.unauthorized)

            return Jwt.decode(token)
        } catch (e: Exception) {
            println(e)
            throw ApiError(ErrorMessage.unauthorized)
        }
    }

    fun checkRoles(
        authorizationField: String?,
        roles: List<String> = Role.adminOnly
    ) {
        val userData: UserData = getData(authorizationField)

        if (roles.indexOf(userData.role) == -1) {
            throw ApiError(ErrorMessage.noAccess)
        }
    }
}