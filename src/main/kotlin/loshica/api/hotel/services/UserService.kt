package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IUserService
import loshica.api.hotel.models.User
import loshica.api.hotel.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired override val repository: UserRepository
) : BaseService<User, UserRepository>(repository), IUserService {

    override fun getByEmail(email: String): User? = repository.findUserByEmail(
        email = email
    )

    override fun countAmount(): Int = repository.count().toInt()

    override fun create(
        email: String,
        password: String,
        role: String
    ): User {
        val user = User(email = email, password = password, role = role)
        repository.save(user)
        return user
    }

    override fun change(id: Int, role: String): User {
        val user: User = getOne(id)
        user.change(role = role)
        repository.save(user)
        return user
    }
}