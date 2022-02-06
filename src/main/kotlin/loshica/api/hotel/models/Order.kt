package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.BaseOrderDto
import loshica.api.hotel.shared.Constants
import loshica.api.hotel.shared.FieldName
import java.util.*
import javax.persistence.*

@Entity(name = Constants.orderEntity)
class Order(
    @ManyToOne
    @field:JsonProperty(FieldName.basket)
    val basket: Basket,

    @OneToOne @field:JsonProperty(FieldName.room) val room: Room,

    @ManyToMany
    @field:JsonProperty(FieldName.services)
    val services: MutableList<Service> = mutableListOf(),

    val duty: Int = 0,
    val population: Int = 0,
    val date: String = Date().toString(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.basket)
    fun convertBasket(): String = this.basket.id.toString()

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun convertToBaseDto(): BaseOrderDto = BaseOrderDto(
        _basket = this.convertBasket(),
        _room = this.room.convertToBaseDto(),
        _services = this.services,
        duty = this.duty,
        population = this.population,
        date = this.date,
        _id = this.convertId()
    )
}