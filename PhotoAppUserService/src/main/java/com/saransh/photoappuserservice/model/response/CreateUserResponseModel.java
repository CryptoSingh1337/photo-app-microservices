package com.saransh.photoappuserservice.model.response;

import com.saransh.photoappuserservice.domain.Role;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by CryptSingh1337 on 9/3/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateUserResponseModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Collection<Role> roles;
}
