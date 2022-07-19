package com.github.bcmes.demo.externalcalls.swapi

import com.github.bcmes.demo.externalcalls.swapi.circuitbreaker.MyFallback
import com.github.bcmes.demo.externalcalls.swapi.circuitbreaker.MyFallbackFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "SWAPI",
    url = "https://swapi.dev/api"
    ,configuration = [MyInterceptors::class]
    //,fallback = MyFallback::class
    , fallbackFactory = MyFallbackFactory::class
    //, contextId = "ContextoA"
)
interface ExternalCallSwapi {
    @GetMapping("/people/1")
    //@Cacheable(cacheNames = ["etag"], key = "#keyParam") //testar para ver como funciona..
    fun getPeople(): Any
}

/**
 * A propriedade, contextId = "ContextoA" , Serve para diferenciar varios FeignClients com mesmo name e url.
 * Para que eles apontem para o mesmo servidor, mas cada um com uma configuração diferente. Essa propriedade
 * evita a colisão de nomes desses beans.
 */