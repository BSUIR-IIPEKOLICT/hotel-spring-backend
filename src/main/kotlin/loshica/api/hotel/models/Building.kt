package loshica.api.hotel.models

import loshica.api.hotel.core.BaseEntity
import loshica.api.hotel.dtos.BuildingDto
import loshica.api.hotel.dtos.BuildingPopulatedDto
import javax.persistence.*

@Entity
class Building(
    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
    @Column(name = "buildingRooms")
    val rooms: MutableList<Room> = mutableListOf(),

    @Column(unique = true) var address: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0
) : BaseEntity<BuildingDto, BuildingPopulatedDto>() {

    override fun toDto(): BuildingDto {
        return BuildingDto(
            rooms = this.rooms.map { it.id },
            address = this.address,
            id = this.id
        )
    }

    override fun toPopulatedDto(): BuildingPopulatedDto {
        return BuildingPopulatedDto(
            rooms = this.rooms.map { it.toDto() },
            address = this.address,
            id = this.id
        )
    }
}