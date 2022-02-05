package com.github.bcmes.demo.starwars

import com.github.bcmes.demo.externalcalls.swapi.SwApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/character")
class CharacterController(
    private val swApi: SwApi
) {
    @GetMapping("/{idCharacter}")
    fun getCharacter(@PathVariable idCharacter: Int): Any {
        return swApi.getPeople(idCharacter)
    }
}