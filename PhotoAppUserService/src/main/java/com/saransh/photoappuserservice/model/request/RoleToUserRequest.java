package com.saransh.photoappuserservice.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by CryptSingh1337 on 9/6/2021
 */
@Data
public class RoleToUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String role;
}
