package com.saransh.photoappuserservice.controllers;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saransh.photoappuserservice.model.request.CreateUserRequestModel;
import com.saransh.photoappuserservice.model.request.RoleToUserRequest;
import com.saransh.photoappuserservice.model.response.CreateUserResponseModel;
import com.saransh.photoappuserservice.services.UserService;
import com.saransh.photoappuserservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Created by CryptSingh1337 on 8/23/2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment env;
    private final UserService service;
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    @GetMapping("/status/check")
    public String status() {
        return "User controller is working on port: " +
                env.getProperty("local.server.port") + ", secret: " +
                env.getProperty("jwt.token.secret");
    }

    @GetMapping
    public ResponseEntity<List<CreateUserResponseModel>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseModel> saveUser(
            @Validated
            @RequestBody CreateUserRequestModel createUserRequestModel) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveUser(createUserRequestModel));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest req, HttpServletResponse res) {
        String authorizationToken = req.getHeader(AUTHORIZATION);
        String token = jwtUtils.extractAuthorizationToken(authorizationToken);
        if (token != null) {
            try {
                JWTVerifier jwtVerifier = jwtUtils.getVerifier();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String username = decodedJWT.getSubject();
                User user = (User) service.loadUserByUsername(username);
                Map<String, String> tokens = jwtUtils.getTokens(user,
                        decodedJWT, req.getRequestURL().toString());
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                objectMapper.writeValue(res.getWriter(), tokens);
            } catch (Exception e) {
                try {
                    res.setStatus(HttpStatus.FORBIDDEN.value());
                    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(res.getWriter(), e.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping("/role/add")
    public ResponseEntity<?> addRoleToUser(
            @Validated @RequestBody RoleToUserRequest userRole) {
        service.addRoleToUser(userRole.getUsername(), userRole.getRole());
        return ResponseEntity.ok().build();
    }
}
