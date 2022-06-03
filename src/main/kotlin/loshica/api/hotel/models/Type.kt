package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.TypeDto
import loshica.api.hotel.dtos.TypePopulatedDto
import javax.persistence.*

@Entity
class Type(
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    val rooms: MutableSet<Room> = mutableSetOf(),

    @Column(unique = true) var name: String = "",
    var options: String = "no options",
    var price: Int = 0,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<TypeDto, TypePopulatedDto>() {

    override fun toDto(): TypeDto {
        return TypeDto(
            rooms = this.rooms.map { it.id },
            name = this.name,
            options = this.options,
            price = this.price,
            id = this.id
        )
    }

    override fun toPopulatedDto(): TypePopulatedDto {
        return TypePopulatedDto(
            rooms = this.rooms.map { it.toDto() },
            name = this.name,
            options = this.options,
            price = this.price,
            id = this.id
        )
    }
}