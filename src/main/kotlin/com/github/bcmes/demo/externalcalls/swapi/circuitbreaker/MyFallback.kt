package com.github.bcmes.demo.externalcalls.swapi.circuitbreaker

import com.github.bcmes.demo.externalcalls.swapi.ExternalCallSwapi
import org.springframework.stereotype.Component

/**
 * Esta e a forma mais basica de implementacao do fallback, neste formato NAO temos
 * o motivo do disparo do fallback.
 * Este aqui ira sobrepor a implementacao do ErrorDecoder, por exemplo, pois sempre
 * que o circuito for aberto, caira aqui.
 */
@Component
class MyFallback : ExternalCallSwapi {
    override fun getPeople(idPeople: Int): Any {
        return object {
            val description = "Retorno de fallback padrao."
        }
    }
}