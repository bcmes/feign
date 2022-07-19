package com.github.bcmes.demo.externalcalls.swapi.circuitbreaker

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


//@Configuration //Se este bean for gerado, ele tera precedencia sobre a configuracao do .properties
class ConfigureCircuitBreaker {
    @Bean
    fun configureResilience4J() = Customizer<Resilience4JCircuitBreakerFactory> { resilience4JCircuitBreakerFactory ->
        resilience4JCircuitBreakerFactory.configureDefault {
                Resilience4JConfigBuilder(it)
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(1)).build())
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .build()
            }
    }
}