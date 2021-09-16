package com.saransh.photoappalbumservice.controllers;

import com.saransh.photoappalbumservice.model.request.AlbumRequestModel;
import com.saransh.photoappalbumservice.model.response.AlbumResponseModel;
import com.saransh.photoappalbumservice.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/{username}/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponseModel>> getAllAlbums(@PathVariable String username) {
        return ResponseEntity.ok(albumService.getAllAlbums(username));
    }

    @PostMapping
    public ResponseEntity<AlbumResponseModel> saveAlbum(
            @PathVariable String username,
            @Validated @RequestBody AlbumRequestModel album) {
        return ResponseEntity.status(CREATED).body(albumService.saveAlbum(username, album));
    }
}
