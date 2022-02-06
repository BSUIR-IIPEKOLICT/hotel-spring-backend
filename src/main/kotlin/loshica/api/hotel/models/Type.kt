package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Type(
    @Column(unique = true) var name: String = "",
    var places: Int = 0,

    @ManyToMany
    @field:JsonProperty(FieldName.services)
    var services: MutableList<Service> = mutableListOf(),

    @OneToMany var rooms: MutableList<Room> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.services)
    fun convertServices(): List<String> = this.services.map {
        service: Service -> "${service.id}"
    }

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun change(name: String, places: Int, services: List<Service>) {
        this.name = name
        this.places = places
        this.services = services.toMutableList()
    }
}