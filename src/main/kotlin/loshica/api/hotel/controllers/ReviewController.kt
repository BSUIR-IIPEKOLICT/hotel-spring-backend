package loshica.api.hotel.controllers

import loshica.api.hotel.dtos.ReviewDto
import loshica.api.hotel.models.Review
import loshica.api.hotel.services.ReviewService
import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.review)
class ReviewController(private val reviewService: ReviewService) {

    @GetMapping
    fun getAll(@RequestParam _room: String): Iterable<Review> {
        return reviewService.getByRoom(_room.toInt())
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody dto: ReviewDto): Review {
        return reviewService.create(dto._room.toInt(), dto.author, dto.content)
    }

    @PatchMapping
    @ResponseBody
    fun change(@RequestBody dto: ReviewDto): Review {
        return reviewService.change(dto._id.toInt(), dto.content)
    }

    @DeleteMapping
    fun delete(@RequestBody dto: ReviewDto): String {
        return reviewService.delete(dto._id.toInt()).toString()
    }
}