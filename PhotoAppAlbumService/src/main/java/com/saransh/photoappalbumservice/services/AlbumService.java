package com.saransh.photoappalbumservice.services;

import com.saransh.photoappalbumservice.model.request.AlbumRequestModel;
import com.saransh.photoappalbumservice.model.response.AlbumResponseModel;

import java.util.List;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
public interface AlbumService {

    List<AlbumResponseModel> getAllAlbums(String username);
    AlbumResponseModel saveAlbum(String username, AlbumRequestModel album);
}
