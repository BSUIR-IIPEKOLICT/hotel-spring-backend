package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Building(
    @field:JsonProperty(FieldName.roomsIds) val roomsIds: Int = 0,
    @Column(unique = true) var address: String = "",

    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
) {

    @JsonGetter(FieldName.roomsIds)
    fun getRoomsIds(): String = this.roomsIds.toString()

    @JsonGetter(FieldName.id)
    fun getId(): String = this.id.toString()
}