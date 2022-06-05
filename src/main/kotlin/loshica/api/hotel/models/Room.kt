package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.RoomDto
import loshica.api.hotel.dtos.RoomPopulatedDto
import javax.persistence.*

@Entity
class Room(
    @ManyToOne var type: Type,

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    val comments: MutableSet<Comment> = mutableSetOf(),

    var description: String = "",
    var address: String = "",
    var floor: Int = 1,
    var places: Int = 1,
    var isFree: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<RoomDto, RoomPopulatedDto>() {

    override fun toDto(): RoomDto {
        return RoomDto(
            type = type.id,
            comments = comments.map { it.id },
            description = description,
            address = address,
            floor = floor,
            places = places,
            isFree = isFree,
            id = id
        )
    }

    override fun toPopulatedDto(): RoomPopulatedDto {
        return RoomPopulatedDto(
            type = type.toDto(),
            comments = comments.map { it.toDto() },
            description = description,
            address = address,
            floor = floor,
            places = places,
            isFree = isFree,
            id = id
        )
    }
}