package com.tattooshop.shop.model.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException() {
            super("Empty Field exception! ");
    }
}
