package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Review(
    @field:JsonProperty(FieldName.roomId) val roomId: Int = 0,
    val author: String = "",
    var content: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.roomId)
    fun getRoomId(): String = this.roomId.toString()

    @JsonGetter(FieldName.id)
    fun getId(): String = this.id.toString()
}