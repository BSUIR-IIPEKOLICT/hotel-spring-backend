package loshica.api.hotel.services

import loshica.api.hotel.models.Review
import loshica.api.hotel.repositories.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewService(@Autowired private val reviewRepository: ReviewRepository) {

    fun getByRoom(roomId: Int): Iterable<Review> = reviewRepository.findByRoomId(roomId)

    fun getOne(_id: Int): Review? = reviewRepository.findByIdOrNull(_id)

    fun create(roomId: Int, author: String, content: String): Review {
        val review = Review(roomId, author, content)
        reviewRepository.save(review)
        return review
    }

    fun change(id: Int, content: String): Review {
        val review: Review = reviewRepository.findByIdOrNull(id) ?: throw Exception("not found")
        review.content = content
        reviewRepository.save(review)
        return review
    }

    fun delete(id: Int): Int {
        reviewRepository.deleteById(id)
        return id
    }

    fun deleteWithRoom(roomId: Int) = reviewRepository.deleteByRoomId(roomId)
}