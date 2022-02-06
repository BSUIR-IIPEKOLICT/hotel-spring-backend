package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Service(
    @Column(unique = true) var name: String = "",
    var price: Int = 0,

    @ManyToMany var types: MutableList<Type> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun change(name: String, price: Int) {
        this.name = name
        this.price = price
    }
}