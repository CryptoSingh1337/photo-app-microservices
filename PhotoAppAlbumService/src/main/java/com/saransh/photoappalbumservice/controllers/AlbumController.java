package com.saransh.photoappalbumservice.controllers;

import com.saransh.photoappalbumservice.model.request.AlbumRequestModel;
import com.saransh.photoappalbumservice.model.response.AlbumResponseModel;
import com.saransh.photoappalbumservice.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/{userId}/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponseModel>> getAllAlbums(@PathVariable UUID userId) {
        return ResponseEntity.ok(albumService.getAllAlbums(userId));
    }

    @PostMapping
    public ResponseEntity<AlbumResponseModel> saveAlbum(
            @PathVariable UUID userId,
            @Validated @RequestBody AlbumRequestModel album) {
        return ResponseEntity.status(CREATED).body(albumService.saveAlbum(userId, album));
    }
}
