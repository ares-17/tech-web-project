package com.unina.techweb.exceptions;

public class MultipleEntitiesException extends RuntimeException {

    public MultipleEntitiesException(){ }

    public MultipleEntitiesException(String id) {
        super("Not found by id: " + id);
    }
}
