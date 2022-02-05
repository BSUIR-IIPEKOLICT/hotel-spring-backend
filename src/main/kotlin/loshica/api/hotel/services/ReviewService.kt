package loshica.api.hotel.services

import loshica.api.hotel.models.Review
import loshica.api.hotel.models.Room
import loshica.api.hotel.repositories.ReviewRepository
import loshica.api.hotel.shared.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewService(@Autowired private val reviewRepository: ReviewRepository) {

    fun getByRoom(room: Room): Iterable<Review> = reviewRepository.findByRoom(room)

    fun getOne(_id: Int): Review? = reviewRepository.findByIdOrNull(_id)

    fun create(roomId: Room, author: String, content: String): Review {
        val review = Review(roomId, author, content)
        reviewRepository.save(review)
        return review
    }

    fun change(id: Int, content: String): Review {
        val review: Review = reviewRepository.findByIdOrNull(id) ?: throw Exception(Constants.notFoundMessage)
        review.content = content
        reviewRepository.save(review)
        return review
    }

    fun delete(id: Int): Int {
        reviewRepository.deleteById(id)
        return id
    }

    fun deleteWithRoom(room: Room) = reviewRepository.deleteByRoom(room)
}