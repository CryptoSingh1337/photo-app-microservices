package com.saransh.photoappapigateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by CryptSingh1337 on 9/6/2021
 */
@Component
public class JwtUtils {

    private final Environment env;

    public JwtUtils(Environment env) {
        this.env = env;
    }

    public DecodedJWT decodedJWT(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        String authorizationToken = token.substring("Bearer ".length());
        return verifier.verify(authorizationToken);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(Objects
                .requireNonNull(env.getProperty("jwt.token.secret")));
    }
}
