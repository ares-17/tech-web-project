package com.unina.techweb.exceptions;

import org.springframework.http.HttpStatus;

public class TWNotFoundException extends TechWebException{

    public TWNotFoundException(String key){
        super(HttpStatus.NOT_FOUND, "Element not found with: ".concat(key), "Element not found");
    }
}
