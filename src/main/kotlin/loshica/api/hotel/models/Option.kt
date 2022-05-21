package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.OptionDto
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
class Option(
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    val bookings: MutableList<Booking> = mutableListOf(),

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    val types: MutableList<Type> = mutableListOf(),

    @Column(unique = true) var name: String = "",
    var price: Int = 0,
    var isActive: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<OptionDto, OptionDto>() {

    fun change(name: String, price: Int) {
        this.name = name
        this.price = price
    }

    override fun toDto(): OptionDto {
        return OptionDto(name = this.name, price = this.price, id = this.id)
    }
}