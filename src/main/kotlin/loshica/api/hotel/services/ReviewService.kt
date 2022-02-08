package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IReviewService
import loshica.api.hotel.models.Review
import loshica.api.hotel.models.Room
import loshica.api.hotel.repositories.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService(
    @Autowired override val repository: ReviewRepository
) : BaseService<Review, ReviewRepository>(repository), IReviewService {

    override fun getByRoom(room: Room): Iterable<Review> = repository.findByRoom(room)

    override fun create(roomId: Room, author: String, content: String): Review {
        val review = Review(roomId, author, content)
        repository.save(review)
        return review
    }

    override fun change(id: Int, content: String): Review {
        val review: Review = this.getOne(id)
        review.change(content = content)
        repository.save(review)
        return review
    }

    override fun deleteWithRoom(room: Room) = repository.findByRoom(room).forEach {
        repository.delete(it)
    }
}