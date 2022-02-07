package com.github.bcmes.demo.externalcalls.swapi

import com.github.bcmes.demo.externalcalls.swapi.circuitbreaker.SwApiFallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "SWAPI",
    url = "https://swapi.dev/api"
    ,configuration = [MyRequestInterceptor::class]
    //,fallback = SwApiFallback::class
    , fallbackFactory = SwApiFallbackFactory::class
)
interface SwApi {

    @GetMapping("/people/{idPeople}")
    fun getPeople(@PathVariable idPeople: Int): Any
}