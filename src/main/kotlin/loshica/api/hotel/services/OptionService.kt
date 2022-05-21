package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IOptionService
import loshica.api.hotel.models.Booking
import loshica.api.hotel.models.Option
import loshica.api.hotel.models.Type
import loshica.api.hotel.repositories.OptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date

@Service
class OptionService(
    @Autowired override val repository: OptionRepository
) : BaseService<Option, OptionRepository>(repository),
    IOptionService {

    override fun getByIds(ids: List<Int>): List<Option> {
        return repository.findByIdIsIn(ids.toMutableList()).toList()
    }

    override fun create(name: String, price: Int): Option {
        val option = Option(name = name, price = price)
        repository.save(option)
        return option
    }

    override fun change(id: Int, name: String, price: Int): Option {
        val option: Option = this.getOne(id)
        option.change(name = name, price = price)
        repository.save(option)
        return option
    }

    override fun addBooking(booking: Booking) {
        booking.options.forEach {
            it.bookings.add(booking)
            repository.save(it)
        }
    }

    override fun removeBooking(booking: Booking) {
        booking.options.forEach {
            it.bookings.remove(booking)
            repository.save(it)
        }
    }

    override fun addType(type: Type) {
        type.options.forEach {
            it.types.add(type)
            repository.save(it)
        }
    }

    override fun removeType(type: Type) {
        type.options.forEach {
            it.types.add(type)
            repository.save(it)
        }
    }

    override fun disable(id: Int): Int {
        val option: Option = getOne(id)
        option.isActive = false
        option.name = "${Date().time}-${Math.random()}"
        repository.save(option)
        return id
    }
}