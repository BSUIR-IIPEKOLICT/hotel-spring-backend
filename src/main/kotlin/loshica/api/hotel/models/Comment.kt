package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.CommentDto
import loshica.api.hotel.dtos.CommentPopulatedDto
import javax.persistence.*

@Entity
class Comment(
    @ManyToOne val room: Room,

    var content: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<CommentDto, CommentPopulatedDto>() {

    override fun toDto(): CommentDto {
        return CommentDto(room = this.room.id, content = this.content, id = this.id)
    }

    override fun toPopulatedDto(): CommentPopulatedDto {
        return CommentPopulatedDto(room = this.room.toDto(), content = this.content, id = this.id)
    }
}