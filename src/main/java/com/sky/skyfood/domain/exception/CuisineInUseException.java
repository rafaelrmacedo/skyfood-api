package com.sky.skyfood.domain.exception;

import java.io.Serial;

public class CuisineInUseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CuisineInUseException(String message) {
        super(message);
    }
}
