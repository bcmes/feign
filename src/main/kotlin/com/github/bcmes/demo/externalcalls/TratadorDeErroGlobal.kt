package com.github.bcmes.demo.externalcalls

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

/**
 * Quando vc cria configuracoes customizadas para seus beans FeignClient, atraves
 * do @FeignClient(... ,configuration = [ExampleInterceptor::class]), a classe
 * ExampleInterceptor.class, nao deve ser anotada com @Component. Caso seja anotada,
 * ela ira substituir o comportamento padrao para todos os beans FeignClients, que nao
 * possuam um configuration especificado para si.
 *
 */