package com.github.bcmes.demo.externalcalls.swapi

import feign.FeignException
import feign.Logger
import feign.RequestInterceptor
import feign.RetryableException
import feign.Retryer
import feign.auth.BasicAuthRequestInterceptor
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class MyInterceptors {
    /**
     * Intercepto a requisição antes de acontecer, e posso manipular
     * através do RequestTemplate, headers, body, queries, uri, etc..
     */
    @Bean
    fun addHeaders() = RequestInterceptor {
        it.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * Este classe BasicAuthRequestInterceptor é uma RequestInterceptor
     * especializada em adicionar um header basic authentication, o header
     * adicionado aqui, se "somara" aos definidos no RequestInterceptors acima.
     */
    //@Bean
    fun basicAuth() = BasicAuthRequestInterceptor("bruno", "123456")

    /**
     * Este bean e chamado sempre que o retorno e diferente de 2XX.
     * Se a sua exceção for passível de nova tentativa, lance um RetryableException,
     * , que sera recebida pelo metodo continueOrPropagate(RetryableException) do bean Retryer
     */
    @Bean
    fun tratarResponseError() = ErrorDecoder { methodKey, response ->
        when (response.status()) {
            400 -> throw RuntimeException("Method $methodKey gave error 400")
            404 -> throw RuntimeException("Error local 404..")
            500 -> throw RetryableException(
                response.status(),
                response.body().toString(),
                response.request().httpMethod(),
                FeignException.errorStatus(methodKey, response),
                null, //retryAfter = Tentar depois da DateTime EPOC informado.
                response.request()
            )
            else -> throw FeignException.errorStatus(methodKey, response) //So repassando o erro.
        }
    }

    /**
     * Configuracoes de novas tentativas.
     */
    //@Bean
    fun reTentativa(): Retryer {
        return object : Retryer{
            override fun clone(): Retryer {
                TODO("Configura as novas tentativas: tempo de espera, numero de vezes..")
            }

            override fun continueOrPropagate(e: RetryableException?) {
                TODO("Se for permitida uma nova tentativa, caso contrário, propagar a excepção.")
            }
        }
    }

}
