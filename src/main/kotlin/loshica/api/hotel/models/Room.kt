package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.dtos.RoomPopulatedDto
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.*

@Entity
class Room(
    @ManyToOne var building: Building,
    @ManyToOne var type: Type,

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "roomBookings")
    val bookings: MutableList<Booking> = mutableListOf(),

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "roomComments")
    val comments: MutableList<Comment> = mutableListOf(),

    var isFree: Boolean = true,
    var population: Int = 0,
    var bookedUntil: Date? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<RoomDto, RoomPopulatedDto>() {

    fun change(building: Building, type: Type) {
        this.building = building
        this.type = type
    }

    fun book(booking: Booking) {
        this.isFree = false
        this.population = booking.population
        this.bookings.add(booking)
        booking.endDate?.let { this.bookedUntil = it }
    }

    fun unBook(booking: Booking) {
        this.isFree = true
        this.population = 0
        this.bookings.remove(booking)
        this.bookedUntil = null
    }

    override fun toDto(): RoomDto {
        return RoomDto(
            id = this.id,
            building = this.building.id,
            type = this.type.id,
            bookings = this.bookings.map { it.id },
            comments = this.comments.map { it.id },
            isFree = this.isFree,
            population = this.population,
            bookedUntil = this.bookedUntil
        )
    }

    override fun toPopulatedDto(): RoomPopulatedDto {
        return RoomPopulatedDto(
            id = this.id,
            building = this.building.toDto(),
            type = this.type.toDto(),
            bookings = this.bookings.map { it.toDto() },
            comments = this.comments.map { it.toDto() },
            isFree = this.isFree,
            population = this.population,
            bookedUntil = this.bookedUntil
        )
    }
}