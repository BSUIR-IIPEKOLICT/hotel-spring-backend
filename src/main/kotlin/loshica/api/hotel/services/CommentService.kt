package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.ICommentService
import loshica.api.hotel.models.Comment
import loshica.api.hotel.models.Room
import loshica.api.hotel.repositories.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService(
    @Autowired override val repository: CommentRepository
) : BaseService<Comment, CommentRepository>(repository), ICommentService {

    override fun create(room: Room, content: String): Comment {
        val comment = Comment(room = room, content = content)
        repository.save(comment)
        return comment
    }

    override fun change(id: Int, content: String): Comment {
        val comment: Comment = getOne(id)
        comment.content = content
        repository.save(comment)
        return comment
    }

    override fun deleteWithRoom(room: Room) {
        repository.deleteAll(room.comments)
    }
}