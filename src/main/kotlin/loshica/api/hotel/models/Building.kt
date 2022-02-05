package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Building(
    @Column(unique = true) var address: String = "",

    @OneToMany @field:JsonProperty(FieldName.rooms) var rooms: MutableList<Room> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.rooms)
    fun convertRooms(): List<String> = this.rooms.map { room: Room -> room.id.toString() }

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    override fun toString(): String {
        return """
            {
                "id": "${this.convertId()}",
                "_rooms": ${this.convertRooms()},
                "address": "${this.address}"
            }
        """.trimIndent()
    }
}