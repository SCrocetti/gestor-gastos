package com.dev.gestorgastos.persistence.exception;

public class EntityCannotBeUndeletedException extends RuntimeException {
    public EntityCannotBeUndeletedException(String message) {
        super(message);
    }
}
