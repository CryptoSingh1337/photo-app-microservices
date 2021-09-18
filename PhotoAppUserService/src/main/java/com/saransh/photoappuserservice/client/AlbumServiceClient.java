package com.saransh.photoappuserservice.client;

import com.saransh.photoappuserservice.model.response.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by CryptSingh1337 on 9/18/2021
 */
@FeignClient(name = "album-ws")
public interface AlbumServiceClient {

    @GetMapping("/user/{username}/albums")
    List<AlbumResponseModel> getAllAlbums(@PathVariable String username);
}
