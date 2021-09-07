package com.saransh.photoappapigateway.config;

import com.saransh.photoappapigateway.filter.AuthorizationHeaderFilter;
import com.saransh.photoappapigateway.utils.JwtUtils;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpMethod.*;

/**
 * Created by CryptSingh1337 on 9/4/2021
 */
@Configuration
public class LoadBalancedRouteConfig {

    @Bean
    public RouteLocator loadBalancedRoute(RouteLocatorBuilder builder,
                                          AuthorizationHeaderFilter authFilter) {
        return builder.routes()
                .route("users-ws-users",
                        r -> r.path("/users-ws/api/v1/users/register").or()
                                .path("/users-ws/users/login").or()
                                .path("/users-ws/api/v1/users/token/refresh").and()
                                .method(GET, POST)
                                .filters(f ->
                                        f.rewritePath("/users-ws/(?<segment>.*)",
                                                "/${segment}"))
                                .uri("lb://users-ws"))
                .route("users-ws",
                        r -> r.path("/users-ws/**").and()
                                .method(GET, POST, PUT, DELETE).and()
                                .header("Authorization", "Bearer (.*)")
                                .filters(f ->
                                        f.rewritePath("/users-ws/(?<segment>.*)",
                                                        "/${segment}")
                                                .filter(authFilter.apply(
                                                        new AuthorizationHeaderFilter.Config()))
                                ).uri("lb://users-ws"))
                .build();
    }
}
