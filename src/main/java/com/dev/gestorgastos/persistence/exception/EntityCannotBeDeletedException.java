package com.dev.gestorgastos.persistence.exception;

public class EntityCannotBeDeletedException extends RuntimeException {
    public EntityCannotBeDeletedException(String message) {
        super(message);
    }
}
