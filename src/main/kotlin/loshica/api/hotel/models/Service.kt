package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class Service(
    @Column(unique = true) var name: String = "",
    var price: Int = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    override fun toString(): String {
        return """
            {
                "_id": "${this.convertId()}",
                "name": "${this.name}",
                "price": ${this.price}
            }
        """.trimIndent()
    }
}