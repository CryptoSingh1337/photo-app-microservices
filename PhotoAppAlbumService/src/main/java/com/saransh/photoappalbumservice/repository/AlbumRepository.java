package com.saransh.photoappalbumservice.repository;

import com.saransh.photoappalbumservice.domain.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findAllByUserId(UUID userId);
}
