package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.ReviewDto
import loshica.api.hotel.interfaces.IReviewService
import loshica.api.hotel.interfaces.IRoomService
import loshica.api.hotel.models.Review
import loshica.api.hotel.models.Room
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.review)
class ReviewController(
    private val reviewService: IReviewService,
    private val roomService: IRoomService
) {

    @GetMapping
    fun getAll(@RequestParam _room: Int): Iterable<Review> {
        val room: Room = roomService.getOne(_room)
        return reviewService.getByRoom(room)
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: ReviewDto): Review {
        val room: Room = roomService.getOne(dto._room.toInt())
        return reviewService.create(room, dto.author, dto.content)
    }

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: ReviewDto): Review = reviewService.change(
        id = dto._id.toInt(),
        content = dto.content
    )

    @DeleteMapping
    fun delete(@RequestBody dto: ReviewDto): String = reviewService
        .delete(id = dto._id.toInt())
        .toString()
}