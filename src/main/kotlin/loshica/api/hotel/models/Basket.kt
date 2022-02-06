package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Basket(
    @OneToOne
    @field:JsonProperty(FieldName.user)
    val user: User,

    @OneToMany
    @field:JsonProperty(FieldName.orders)
    var orders: MutableList<Order> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()
}