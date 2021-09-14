package com.saransh.photoappalbumservice.mapper;

import com.saransh.photoappalbumservice.domain.Album;
import com.saransh.photoappalbumservice.model.request.AlbumRequestModel;
import com.saransh.photoappalbumservice.model.response.AlbumResponseModel;
import org.mapstruct.Mapper;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Mapper
public interface AlbumMapper {

    AlbumResponseModel albumToResponseAlbum(Album album);
    Album albumRequestToAlbum(AlbumRequestModel albumRequestModel);
}
