package com.tattooshop.shop.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super(String.format("The username: %s is already taken!! ", username));
    }
}