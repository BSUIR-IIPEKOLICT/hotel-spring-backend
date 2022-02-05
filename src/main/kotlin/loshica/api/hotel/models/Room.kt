package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Room(
    @field:JsonProperty(FieldName.building) var building: Int = 0,
    @field:JsonProperty(FieldName.type) var type: Int = 0,
    @field:JsonProperty(FieldName.order) var orderField: Int? = null,
    @field:JsonProperty(FieldName.isFree) var isFree: Boolean = true,
    var population: Int = 0,

    @OneToMany var reviews: MutableList<Review> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.building)
    fun convertBuilding(): String = this.building.toString()

    @JsonGetter(FieldName.type)
    fun convertType(): String = this.type.toString()

    @JsonGetter(FieldName.order)
    fun convertOrder(): String? = this.orderField?.toString()

    @JsonGetter(FieldName.isFree)
    fun convertIsFree(): Boolean = this.isFree

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()
}