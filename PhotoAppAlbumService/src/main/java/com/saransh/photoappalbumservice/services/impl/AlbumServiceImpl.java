package com.saransh.photoappalbumservice.services.impl;

import com.saransh.photoappalbumservice.domain.Album;
import com.saransh.photoappalbumservice.mapper.AlbumMapper;
import com.saransh.photoappalbumservice.model.request.AlbumRequestModel;
import com.saransh.photoappalbumservice.model.response.AlbumResponseModel;
import com.saransh.photoappalbumservice.repository.AlbumRepository;
import com.saransh.photoappalbumservice.services.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public List<AlbumResponseModel> getAllAlbums(String username) {
        log.debug("Retrieving all the albums for the user with username: {}", username);
        return albumRepository.findAllByUsername(username).stream()
                .map(albumMapper::albumToResponseAlbum)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AlbumResponseModel saveAlbum(String username, AlbumRequestModel album) {
        log.debug("Saving Album to user with username: {}", username);
        Album tempAlbum = albumMapper.albumRequestToAlbum(album);
        tempAlbum.setUsername(username);
        Album savedAlbum = albumRepository.save(tempAlbum);
        return albumMapper.albumToResponseAlbum(savedAlbum);
    }
}
