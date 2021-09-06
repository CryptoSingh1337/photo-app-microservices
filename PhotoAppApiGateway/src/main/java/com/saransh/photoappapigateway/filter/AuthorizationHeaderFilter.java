package com.saransh.photoappapigateway.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.saransh.photoappapigateway.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by CryptSingh1337 on 9/6/2021
 */
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JwtUtils jwtUtils;

    public AuthorizationHeaderFilter(JwtUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            if (!req.getHeaders().containsKey(AUTHORIZATION))
                return onError(exchange, "No authorization header", UNAUTHORIZED);
            String token = Objects.requireNonNull(req.getHeaders().get(AUTHORIZATION)).get(0);
            try {
                DecodedJWT decodedToken = jwtUtils.decodedJWT(token);
                String username = decodedToken.getSubject();
                if (username == null || username.isEmpty())
                    return onError(exchange, "Invalid token", UNAUTHORIZED);
                return chain.filter(exchange);
            } catch (JWTVerificationException e) {
                return onError(exchange, e.getMessage(), UNAUTHORIZED);
            }
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange,
                               String err,
                               HttpStatus status) {
        ServerHttpResponse res = exchange.getResponse();
        res.setStatusCode(status);
        byte[] bytes = err.getBytes(UTF_8);
        DataBuffer db = res.bufferFactory().wrap(bytes);
        return res.writeWith(Flux.just(db));
    }

    public static class Config {
    }
}
