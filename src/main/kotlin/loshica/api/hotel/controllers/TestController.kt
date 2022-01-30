package loshica.api.hotel.controllers

import loshica.api.hotel.shared.Route
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Route.test)
class TestController {

    @GetMapping
    fun getHello(): String {
        return "hello"
    }

    @GetMapping("{id}")
    fun getIndex(@PathVariable id: String): String {
        return id
    }

    @GetMapping("lol")
    fun getQuery(@RequestParam id: String): String {
        return id
    }
}