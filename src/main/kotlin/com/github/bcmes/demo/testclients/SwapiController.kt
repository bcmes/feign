package com.github.bcmes.demo.testclients

import com.github.bcmes.demo.externalcalls.swapi.ExternalCallSwapi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/character")
class SwapiController(
    private val externalCallSwapi: ExternalCallSwapi
) {
    @GetMapping("/{idCharacter}")
    fun getCharacter(@PathVariable idCharacter: Int): Any {
        return externalCallSwapi.getPeople()
    }
}