package com.saransh.photoappapigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * Created by CryptSingh1337 on 9/7/2021
 */
@Configuration
public class CustomFilters {

    private final Logger logger = LoggerFactory.getLogger(CustomFilters.class);

    @Bean
    @Order(1)
    public GlobalFilter firstCustomPreFilter() {
        return ((exchange, chain) -> {
            logger.info("First Pre filter executed....");
            return chain.filter(exchange);
        });
    }

    @Bean
    @Order(2)
    public GlobalFilter secondCustomPreFilter() {
        return ((exchange, chain) -> {
           logger.info("Second Pre filter executed....");
           return chain.filter(exchange);
        });
    }

    @Bean
    @Order(1)
    public GlobalFilter thirdCustomPostFilter() {
        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
            logger.info("Third Post filter executed....");
        })));
    }

    @Bean
    @Order(2)
    public GlobalFilter fourthCustomPostFilter() {
        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
            logger.info("Fourth Post filter executed....");
        })));
    }
}
