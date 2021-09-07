package com.saransh.photoappuserservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CryptSingh1337 on 9/6/2021
 */
@Component
public class JwtUtils {

    private final Algorithm algorithm;

    public JwtUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    }

    public String generateAccessToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String extractAuthorizationToken(String token) {
        if (token != null && token.startsWith("Bearer "))
            return token.substring("Bearer ".length());
        return null;
    }

    public Map<String, String> getTokens(User user,
                                         DecodedJWT decodedJWT,
                                         String issuer) {
        if (!user.getUsername().equals(decodedJWT.getSubject()))
            throw new RuntimeException("Invalid refresh token");
        String accessToken = generateAccessToken(user, issuer);
        String refreshToken = generateRefreshToken(user, issuer);
        Map<String, String> tokens = new HashMap<>(2);
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }

    public JWTVerifier getVerifier() {
        return JWT.require(algorithm).build();
    }
}
