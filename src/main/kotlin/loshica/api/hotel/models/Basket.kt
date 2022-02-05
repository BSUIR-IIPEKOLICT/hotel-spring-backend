package loshica.api.hotel.models

//import com.fasterxml.jackson.annotation.JsonGetter
//import com.fasterxml.jackson.annotation.JsonProperty
//import loshica.api.hotel.shared.FieldName
//import javax.persistence.*
//
//@Entity
//class Basket(
//    @field:JsonProperty(FieldName.userId) @Column(unique = true) val userId: Int = 0,
//    @field:JsonProperty(FieldName.ordersIds) var ordersIds: Int = 0,
//
//    @Id @GeneratedValue @field:JsonProperty(FieldName.id) val id: Int = 0
//) {
//
//    @JsonGetter(FieldName.userId)
//    fun getUserId(): String = this.userId.toString()
//
//    @JsonGetter(FieldName.ordersIds)
//    fun getOrdersIds(): String = this.ordersIds.toString()
//
//    @JsonGetter(FieldName.id)
//    fun getId(): String = this.id.toString()
//}