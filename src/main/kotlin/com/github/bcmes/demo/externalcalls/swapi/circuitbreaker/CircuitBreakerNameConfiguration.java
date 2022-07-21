package com.github.bcmes.demo.externalcalls.swapi.circuitbreaker;

import feign.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class CircuitBreakerNameConfiguration {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerNameConfiguration.class);

    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver() {
        return (String feignClientName, Target<?> target, Method method) -> {
            String circuitBreakerName = feignClientName + "_" + method.getName();
            logger.info("Circuit breaker name: [" + circuitBreakerName + "]");
            return circuitBreakerName;
        };
    }
}
/**
 * O 'bean' acima e para alterar o 'name' gerado para o 'Circuit Breaker', pois por padrao o nome gerado contem um
 * sharp, #, ex.: ExternalCallSwapi#getPeople. E isso obriga a usar aspas duplas, atraves de caracteres de escape,
 * quando queremos informar uma configuracao especifica para um determinado feignClient no '.properties',
 * ex.: resilience4j.timelimiter.instances.\"[ExternalCallSwapi#getPeople()]\".timeoutDuration=10s
 * Devido a isso, podemos alterar o nome, para evitar caracteres rejeitados pelo '.properties', como: # ( ) ,
 */