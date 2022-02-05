package loshica.api.hotel.models

//import com.fasterxml.jackson.annotation.JsonGetter
//import com.fasterxml.jackson.annotation.JsonProperty
//import loshica.api.hotel.shared.FieldName
//import javax.persistence.*
//
//@Entity
//class Type(
//    @field:JsonProperty(FieldName.servicesIds) val servicesIds: Int = 0,
//    @Column(unique = true) var name: String = "",
//    var places: Int = 0,
//
//    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
//) {
//
//    @JsonGetter(FieldName.servicesIds)
//    fun getServicesIds(): String = this.servicesIds.toString()
//
//    @JsonGetter(FieldName.id)
//    fun getId(): String = this.id.toString()
//}