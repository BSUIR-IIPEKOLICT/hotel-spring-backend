package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import loshica.api.hotel.shared.BaseRoomDto
import javax.persistence.*

@Entity
class Room(
    @ManyToOne @field:JsonProperty(FieldName.building) var building: Building,
    @ManyToOne @field:JsonProperty(FieldName.type) var type: Type,
    @OneToOne @field:JsonProperty(FieldName.order) var orderField: Order? = null,
    @field:JsonProperty(FieldName.isFree) var isFree: Boolean = true,
    var population: Int = 0,

//    @OneToMany var reviews: MutableList<Review> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.order)
    fun convertOrder(): String? = this.orderField?.id?.toString()

    @JsonGetter(FieldName.isFree)
    fun convertIsFree(): Boolean = this.isFree

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun change(building: Building, type: Type) {
        this.building = building
        this.type = type
    }

    fun book(order: Order, population: Int) {
        this.isFree = false
        this.orderField = order
        this.population = population
    }

    fun unBook() {
        this.isFree = true
        this.orderField = null
        this.population = 0
    }

    fun convertToBaseDto(): BaseRoomDto = BaseRoomDto(
        _building = this.building.id.toString(),
        _type = this.type.id.toString(),
        _order = this.convertOrder(),
        isFree = this.convertIsFree(),
        population = this.population,
        _id = this.convertId()
    )
}