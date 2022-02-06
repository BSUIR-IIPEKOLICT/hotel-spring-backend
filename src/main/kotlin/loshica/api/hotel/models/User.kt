package loshica.api.hotel.models

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonProperty
import loshica.api.hotel.shared.Constants
import loshica.api.hotel.shared.FieldName
import javax.persistence.*

@Entity(name = Constants.userEntity)
class User(
    @Column(unique = true) val email: String = "",
    val password: String = "",
    var role: String = Constants.defaultRole,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:JsonProperty(FieldName.id)
    val id: Int = 0
) {

    @JsonGetter(FieldName.id)
    fun convertId(): String = this.id.toString()

    fun change(role: String) {
        this.role = role
    }
}