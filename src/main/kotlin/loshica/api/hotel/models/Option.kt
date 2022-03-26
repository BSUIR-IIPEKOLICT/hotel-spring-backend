package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.OptionDto
import javax.persistence.*

@Entity
class Option(
    @Column(unique = true) var name: String = "",
    var price: Int = 0,

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