package com.saransh.photoappuserservice.exceptions;

/**
 * Created by CryptSingh1337 on 9/19/2021
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
