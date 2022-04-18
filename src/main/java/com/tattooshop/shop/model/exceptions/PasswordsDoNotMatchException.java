package com.tattooshop.shop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException() {
        super("Your passwords do not match");
    }
}
