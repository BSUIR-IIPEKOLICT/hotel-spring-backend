package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Room(
    @ManyToOne @field:JsonProperty(FieldName.building) var building: Building,
    @ManyToOne @field:JsonProperty(FieldName.type) var type: Type,
    @field:JsonProperty(FieldName.order) var orderField: Int? = null,
    @field:JsonProperty(FieldName.isFree) var isFree: Boolean = true,
    var population: Int = 0,

    @OneToMany var reviews: MutableList<Review> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.order)
    fun convertOrder(): String? = this.orderField?.toString()

    @JsonGetter(FieldName.isFree)
    fun convertIsFree(): Boolean = this.isFree

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun change(building: Building, type: Type) {
        this.building = building
        this.type = type
    }

    fun book(order: Int, population: Int) {
        this.isFree = false
        this.orderField = order
        this.population = population
    }

    fun unBook() {
        this.isFree = true
        this.orderField = null
        this.population = 0
    }
}