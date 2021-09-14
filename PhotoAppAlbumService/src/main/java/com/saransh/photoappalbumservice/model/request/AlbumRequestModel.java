package com.saransh.photoappalbumservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumRequestModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Size(min = 3, max = 20)
    private String description;
}
