package loshica.api.hotel.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
@CrossOrigin(originPatterns = ["*"])
class MainController {

    @GetMapping
    fun healthCheck(): Map<String, String> = mapOf("status" to "ok")
}