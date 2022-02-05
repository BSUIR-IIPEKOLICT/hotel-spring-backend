package loshica.api.hotel.models

//import com.fasterxml.jackson.annotation.JsonGetter
//import com.fasterxml.jackson.annotation.JsonProperty
//import loshica.api.hotel.shared.FieldName
//import java.util.*
//import javax.persistence.*
//
//@Entity
//class Order(
//    @field:JsonProperty(FieldName.basketId) val basketId: Int = 0,
//    @field:JsonProperty(FieldName.roomId) val roomId: Int = 0,
//    @field:JsonProperty(FieldName.servicesIds) val servicesIds: Int = 0,
//    val duty: Int = 0,
//    val population: Int = 0,
//    val date: String = Date().toString(),
//
//    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
//) {
//
//    @JsonGetter(FieldName.basketId)
//    fun getBasketId(): String = this.basketId.toString()
//
//    @JsonGetter(FieldName.roomId)
//    fun getRoomId(): String = this.roomId.toString()
//
//    @JsonGetter(FieldName.servicesIds)
//    fun getServicesIds(): String = this.servicesIds.toString()
//
//    @JsonGetter(FieldName.id)
//    fun getId(): String = this.id.toString()
//}