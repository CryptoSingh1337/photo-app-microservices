package com.saransh.photoappuserservice.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CryptSingh1337 on 8/23/2021
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private Environment env;

    public UsersController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status/check")
    public String status() {
        return "User controller is working on port " + env.getProperty("local.server.port");
    }
}
