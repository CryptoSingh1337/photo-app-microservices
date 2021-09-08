package com.saransh.photoappuserservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by CryptSingh1337 on 9/6/2021
 */
@Component
public class JwtUtils {

    private final Algorithm tokenAlgorithm;
    private final Algorithm refreshTokenAlgorithm;
    private final Environment env;

    public JwtUtils(Environment env) {
        this.env = env;
        this.tokenAlgorithm = Algorithm.HMAC256(
                Objects.requireNonNull(
                        env.getProperty("jwt.token.secret")
                ).getBytes());
        this.refreshTokenAlgorithm = Algorithm.HMAC256(
                Objects.requireNonNull(
                        env.getProperty("jwt.refresh.token.secret")
                ).getBytes());
    }

    public String generateAccessToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() +
                                Integer.parseInt(
                                        Objects.requireNonNull(
                                                env.getProperty("jwt.token.expiration")
                                        ))))
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(tokenAlgorithm);
    }

    public String generateRefreshToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +
                        Integer.parseInt(
                                Objects.requireNonNull(
                                        env.getProperty("jwt.refresh.token.expiration")
                                ))))
                .withIssuer(issuer)
                .sign(refreshTokenAlgorithm);
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
        return JWT.require(refreshTokenAlgorithm).build();
    }
}
