package loshica.api.hotel.repositories

import loshica.api.hotel.models.Comment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : CrudRepository<Comment, Int>