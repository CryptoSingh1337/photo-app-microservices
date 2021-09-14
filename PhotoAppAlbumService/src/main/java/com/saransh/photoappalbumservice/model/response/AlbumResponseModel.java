package com.saransh.photoappalbumservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponseModel {

    private UUID userId;
    private String name;
    private String description;
}
