package com.unina.techweb.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends TechWebException {

    public UserAlreadyExistsException(String username) {
        super(HttpStatus.CONFLICT, "User already exists with username: " + username, "Already exist");
    }
}

