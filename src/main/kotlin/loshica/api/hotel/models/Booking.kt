package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.BookingDto
import loshica.api.hotel.dtos.BookingPopulatedDto
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.Date
import javax.persistence.*

@Entity
class Booking(
    @ManyToOne val room: Room,
    @ManyToOne val createdBy: User,

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    val options: MutableList<Option> = mutableListOf(),

    val price: Int = 0,
    val population: Int = 0,
    val startDate: Date = Date(),
    var endDate: Date?,
    var isActive: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<BookingDto, BookingPopulatedDto>() {

    fun disable() {
        this.endDate = Date()
        this.isActive = false
    }

    override fun toDto(): BookingDto {
        return BookingDto(
            room = this.room.id,
            createdBy = this.createdBy.id,
            options = this.options.filter { it.isActive }.map { it.id },
            price = this.price,
            population = this.population,
            startDate = this.startDate,
            endDate = this.endDate,
            isActive = this.isActive,
            id = this.id
        )
    }

    override fun toPopulatedDto(): BookingPopulatedDto {
        return BookingPopulatedDto(
            room = this.room.toDto(),
            createdBy = this.createdBy.toDto(),
            options = this.options.filter { it.isActive }.map { it.toDto() },
            price = this.price,
            population = this.population,
            startDate = this.startDate,
            endDate = this.endDate,
            isActive = this.isActive,
            id = this.id
        )
    }
}
