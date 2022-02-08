package loshica.api.hotel.interfaces

interface IBaseService<M> {
    fun getAll(): Iterable<M>
    fun getOne(id: Int): M
    fun delete(id: Int): Int
}