package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.Constants
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity
class User(
    @Column(unique = true) val email: String = "",
    val password: String = "",
    var role: String = Constants.defaultRole,

    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
) {

    @JsonGetter(FieldName.id)
    fun getId(): String = this.id.toString()
}