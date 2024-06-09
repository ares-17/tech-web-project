package com.unina.techweb.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){ }

    public NotFoundException(String id) {
        super("Not found by id: " + id);
    }
}
