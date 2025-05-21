package com.tiwilli.gerenciamentoestoque.services.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String msg) {
        super(msg);
    }
}
