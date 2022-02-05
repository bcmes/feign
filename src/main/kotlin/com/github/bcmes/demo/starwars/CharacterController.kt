package com.github.bcmes.demo.starwars

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/character")
class CharacterController {
    @GetMapping("/{idCharacter}")
    fun getCharacter(@PathVariable idCharacter: Int): Any {
        TODO("Chamaremos a api externa aqui.")
    }
}