package com.github.bcmes.demo.externalcalls.swapi.circuitbreaker

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
class ConfigureCircuitBreaker {
    @Bean
    fun configureResilience4J() = Customizer<Resilience4JCircuitBreakerFactory> { resilience4JCircuitBreakerFactory ->
        resilience4JCircuitBreakerFactory.configureDefault {
                Resilience4JConfigBuilder(it)
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(50)).build())
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .build()
            }
    }
}
//TODO: A configuracao acima e global, mas o ideal e a configuracao personalizada
// para cada FeignClient, pois cada host tem suas caracteristicas.