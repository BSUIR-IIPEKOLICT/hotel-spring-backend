package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.models.User
import loshica.api.hotel.repositories.UserRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired override val repository: UserRepository
) : BaseService<User, UserRepository>(repository) {

    fun getByEmail(email: String): Iterable<User> = repository.findUserByEmail(
        email = email
    )

    fun countAmount(): Int = repository.count().toInt()

    fun create(
        email: String,
        password: String,
        role: String = Constants.defaultRole
    ): User {
        val user = User(email = email, password = password, role = role)
        repository.save(user)
        return user
    }

    fun change(id: Int, role: String): User {
        val user: User = getOne(id)
        user.change(role = role)
        repository.save(user)
        return user
    }
}