package com.github.bcmes.demo.externalcalls.swapi.circuitbreaker

import com.github.bcmes.demo.externalcalls.swapi.ExternalCallSwapi
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

/**
 * A vantagem deste e que tenho o motivo da abertura do disjuntor.
 * Se houver uma implementacao de ErrorDecoder, por exemplo, voce
 * podera obter o re-lancamento do erro dela pela "create(cause: Throwable?)"
 */
@Component
class MyFallbackFactory : FallbackFactory<ExternalCallSwapi> {
    override fun create(cause: Throwable?): ExternalCallSwapi {
        return object : ExternalCallSwapi {
            override fun getPeople(): Any {
                return object {
                    val qualABronca = "Error: ${cause?.localizedMessage}"
                }
            }
        }
    }
}