package com.saransh.photoappconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PhotoAppConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppConfigServerApplication.class, args);
    }

}
