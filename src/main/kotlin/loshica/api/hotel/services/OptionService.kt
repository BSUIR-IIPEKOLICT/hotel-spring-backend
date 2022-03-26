package loshica.api.hotel.services

import loshica.api.hotel.core.BaseService
import loshica.api.hotel.interfaces.IOptionService
import loshica.api.hotel.models.Option
import loshica.api.hotel.repositories.OptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
}