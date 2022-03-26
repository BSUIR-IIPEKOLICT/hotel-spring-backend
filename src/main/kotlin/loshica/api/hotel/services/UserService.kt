package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IUserService
import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.User
import loshica.api.hotel.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired override val repository: UserRepository
) : BaseService<User, UserRepository>(repository), IUserService {

    override fun getByEmail(email: String): User? = repository.findByEmail(email)

    override fun getAmount(): Int = repository.count().toInt()

    override fun create(
        email: String,
        password: String,
        role: String
    ): User {
        val user = User(email = email, role = role)
        user.hashPassword(password)
        repository.save(user)
        return user
    }

    override fun change(
        id: Int,
        email: String,
        password: String,
        role: String
    ): User {
        val user: User = getOne(id)
        user.change(email = email, password = password, role = role)
        repository.save(user)
        return user
    }

    override fun addBooking(booking: Booking) {
        booking.createdBy.let {
            it.bookings.add(booking)
            repository.save(it)
        }
    }

    override fun removeBooking(booking: Booking) {
        booking.createdBy.let {
            it.bookings.remove(booking)
            repository.save(it)
        }
    }
}