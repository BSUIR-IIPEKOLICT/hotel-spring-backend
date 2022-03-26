package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.UserDto
import loshica.api.hotel.dtos.UserPopulatedDto
import loshica.api.hotel.shared.Configuration
import loshica.api.hotel.shared.Role
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity(name = "app_user")
class User(
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.EAGER)
    @Column(name = "userBookings")
    val bookings: MutableList<Booking> = mutableListOf(),

    @Column(unique = true) var email: String = "",
    var password: String = "",
    var role: String = Role.CLIENT,
    var isActive: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<UserDto, UserPopulatedDto>() {

    fun hashPassword(password: String) {
        this.password = bcInstance.encode(password)
    }

    fun change(email: String, password: String, role: String) {
        this.email = email
        hashPassword(password)
        this.role = role
    }

    fun setIsActive(value: Boolean) {
        this.isActive = value
    }

    fun comparePasswords(password: String): Boolean = bcInstance.matches(password, this.password)

    companion object {
        private val bcInstance = BCryptPasswordEncoder(Configuration.BCRYPT_STRENGTH)
    }

    override fun toDto(): UserDto {
        return UserDto(
            bookings = this.bookings.map { it.id },
            email = this.email,
            password = this.password,
            role = this.role,
            isActive = this.isActive,
            id = this.id
        )
    }

    override fun toPopulatedDto(): UserPopulatedDto {
        return UserPopulatedDto(
            bookings = this.bookings.map { it.toDto() },
            email = this.email,
            password = this.password,
            role = this.role,
            isActive = this.isActive,
            id = this.id,
        )
    }
}