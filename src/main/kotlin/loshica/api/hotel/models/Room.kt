package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Room(
    @field:JsonProperty(FieldName.buildingId) var buildingId: Int = 0,
    @field:JsonProperty(FieldName.typeId) var typeId: Int = 0,
    @field:JsonProperty(FieldName.orderId) var orderId: Int? = null,
    var isFree: Boolean = true,
    var population: Int = 0,

    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
) {

    @JsonGetter(FieldName.buildingId)
    fun getBuildingId(): String = this.buildingId.toString()

    @JsonGetter(FieldName.typeId)
    fun getTypeId(): String = this.typeId.toString()

    @JsonGetter(FieldName.orderId)
    fun getOrderId(): String = this.orderId.toString()

    @JsonGetter(FieldName.id)
    fun getId(): String = this.id.toString()
}