package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.dtos.TypePopulatedDto
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
class Type(
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    var options: MutableList<Option> = mutableListOf(),

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    @Column(name = "typeRooms")
    val rooms: MutableList<Room> = mutableListOf(),

    @Column(unique = true) var name: String = "",
    var places: Int = 0,
    var price: Int = 0,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<TypeDto, TypePopulatedDto>() {

    fun change(name: String, places: Int, price: Int, options: List<Option>) {
        this.name = name
        this.places = places
        this.price = price
        this.options = options.toMutableList()
    }

    override fun toDto(): TypeDto {
        return TypeDto(
            options = this.options.map { it.id },
            rooms = this.rooms.map { it.id },
            name = this.name,
            places = this.places,
            price = this.price,
            id = this.id
        )
    }

    override fun toPopulatedDto(): TypePopulatedDto {
        return TypePopulatedDto(
            options = this.options.map { it.toDto() },
            rooms = this.rooms.map { it.toDto() },
            name = this.name,
            places = this.places,
            price = this.price,
            id = this.id
        )
    }
}