package loshica.api.hotel.interfaces

import loshica.api.hotel.models.*

interface IOptionService : IBaseService<Option> {
    fun getByIds(ids: List<Int>): List<Option>
    fun create(name: String, price: Int): Option
    fun change(id: Int, name: String, price: Int): Option
}