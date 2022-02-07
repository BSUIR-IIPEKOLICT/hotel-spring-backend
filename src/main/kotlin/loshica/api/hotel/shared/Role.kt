package loshica.api.hotel.shared

object Role {
    const val client = "client"
    const val admin = "admin"
    val adminOnly: List<String> = listOf(admin)
}