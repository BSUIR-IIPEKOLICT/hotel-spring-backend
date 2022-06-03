package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.CommentDto
import loshica.api.hotel.dtos.DeleteDto
import loshica.api.hotel.interfaces.ICommentService
import loshica.api.hotel.interfaces.IRoomService
import loshica.api.hotel.models.Comment
import loshica.api.hotel.shared.Route
import loshica.api.hotel.shared.Selector
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.COMMENTS)
@CrossOrigin(originPatterns = ["*"])
class CommentController(
    private val commentService: ICommentService,
    private val roomService: IRoomService
) {

    @GetMapping(Selector.ID)
    fun getOne(@PathVariable id: Int): CommentDto = commentService.getOne(id).toDto()

    @PostMapping
    fun create(@RequestBody dto: CommentDto): CommentDto {
        return commentService.create(room = roomService.getOne(dto.room), content = dto.content).toDto()
    }

    @PutMapping(Selector.ID)
    fun change(
        @RequestBody dto: CommentDto,
        @PathVariable id: Int
    ): CommentDto {
        return commentService.change(id = id, content = dto.content).toDto()
    }

    @DeleteMapping(Selector.ID)
    fun delete(@PathVariable id: Int): DeleteDto {
        val comment: Comment = commentService.getOne(id)
        roomService.removeComment(comment)
        return DeleteDto(id = commentService.delete(id))
    }
}