package com.saransh.photoappuserservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptSingh1337 on 9/16/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
}
