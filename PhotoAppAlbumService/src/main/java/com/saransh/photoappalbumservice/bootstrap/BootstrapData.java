package com.saransh.photoappalbumservice.bootstrap;

import com.saransh.photoappalbumservice.domain.Album;
import com.saransh.photoappalbumservice.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final AlbumRepository albumRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (albumRepository.count() == 0) {
            log.debug("Saving albums...");
            saveAlbum(Album.builder().username("margie").name("Album - 1").description("Album - 1").build());
            saveAlbum(Album.builder().username("darrell").name("Album - 2").description("Album - 2").build());
        }
        log.debug("Saved albums: {}", albumRepository.count());
    }

    private void saveAlbum(Album album) {
        albumRepository.save(album);
    }
}
