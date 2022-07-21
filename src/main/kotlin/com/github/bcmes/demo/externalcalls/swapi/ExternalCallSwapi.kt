package com.github.bcmes.demo.externalcalls.swapi

import com.github.bcmes.demo.externalcalls.swapi.circuitbreaker.MyFallback
import com.github.bcmes.demo.externalcalls.swapi.circuitbreaker.MyFallbackFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    //Quando eu informo o 'name', o valor do 'contextId' sera igual ao do 'name', caso o 'contextId' nao seja informado.
    name = "SWAPI",
    url = "https://swapi.dev/api"
    ,configuration = [MyInterceptors::class]
    //,fallback = MyFallback::class
    , fallbackFactory = MyFallbackFactory::class
    //Esse valor aqui que vai no '.properties' se eu quiser especificar uma CONFIGURACAO FEIGN so para este client,
    // ex.: feign.client.config.ContextoA.logger-level=FULL
    //, contextId = "ContextoA"
)
interface ExternalCallSwapi {
    @GetMapping("/people/{idPeople}")
    //@Cacheable(cacheNames = ["etag"], key = "#keyParam") //testar para ver como funciona..
    fun getPeople(@PathVariable idPeople: Int): Any
}

/**
 * A propriedade, contextId = "ContextoA" , Serve para diferenciar varios FeignClients com mesmo name e url.
 * Para que eles apontem para o mesmo servidor, mas cada um com uma configuração diferente. Essa propriedade
 * evita a colisão de nomes desses beans.
 */