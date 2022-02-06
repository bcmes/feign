package com.github.bcmes.demo.externalcalls.swapi

import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * Se vc criar beans customizados para as configuracoes do Feign,
 * vc nao precisa vincula-lo a cada FeignClient criado atraves da propredade 'configuration',
 * mas caso vc vincule, ela tera precedencia maior aos beans ja gerados globalmente.
 */
@Component
class TratadorDeErroGlobal {
    @Bean
    fun tratarError() = ErrorDecoder { methodKey, response ->
        throw RuntimeException("ERROR GLOBAL 404")
    }
}