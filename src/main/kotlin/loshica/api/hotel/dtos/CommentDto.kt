package loshica.api.hotel.dtos

data class CommentDto (
    val room: Int,
    val createdBy: Int = 0,
    val content: String = "",
    val id: Int = 0
)