package com.saransh.photoappalbumservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by CryptSingh1337 on 9/14/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private UUID userId;
    private String name;
    private String description;
}
