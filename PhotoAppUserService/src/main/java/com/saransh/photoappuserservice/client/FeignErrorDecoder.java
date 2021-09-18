package com.saransh.photoappuserservice.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by CryptSingh1337 on 9/18/2021
 */
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User's album not found");
    }
}
