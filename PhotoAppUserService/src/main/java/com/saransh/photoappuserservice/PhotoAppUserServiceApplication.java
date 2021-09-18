package com.saransh.photoappuserservice;

import com.saransh.photoappuserservice.client.FeignErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PhotoAppUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppUserServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(15))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.BASIC;
    }

    @Bean
    public ErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
