package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Review(
    @ManyToOne @field:JsonProperty(FieldName.room) val room: Room,
    val author: String = "",
    var content: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.room)
    fun convertRoom(): String = this.room.id.toString()

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()
}