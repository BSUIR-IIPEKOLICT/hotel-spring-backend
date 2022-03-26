package loshica.api.hotel.core

abstract class BaseEntity<Dto, PopulatedDto> {
    open fun toDto(): Dto? = null
    open fun toPopulatedDto(): PopulatedDto? = null
}